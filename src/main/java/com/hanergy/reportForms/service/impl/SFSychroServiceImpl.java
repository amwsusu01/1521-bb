package com.hanergy.reportForms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.enums.EnumVerifySource;
import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.commons.utils.LogUtils;
import com.hanergy.reportForms.commons.utils.ResResult;
import com.hanergy.reportForms.dto.sf.SFCadidate;
import com.hanergy.reportForms.mapper.entity.*;
import com.hanergy.reportForms.service.*;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SFSychroServiceImpl
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/20 13:47
 * @Version 1.0
 **/
@Service
public class SFSychroServiceImpl implements ISFSychroService {

    private Logger logger = LogUtils.getExceptionLogger();

    @Autowired
    private IBeSelectedService beSelectedService;
    @Autowired
    private IEducationBackgroundService educationBackgroundService;
    @Autowired
    private IExperienceService experienceService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGenerateCodeService generateCodeService;

    @Autowired
    private ICredentialsService credentialsService;

    @Autowired
    private ICheckService checkService;

    @Override
    public JSONObject saveResumes(List<SFCadidate> cadidates) {
        if (cadidates == null || cadidates.size() == 0) {
            return ResResult.failed("传递数据不可为空!");
        }

        if (!CollectionUtils.isEmpty(cadidates)) {
            for (SFCadidate cadidate : cadidates) {
                // 保存员工信息,返回员工ID
                Long beSelected = saveBeSelected(cadidate);

                /**
                 * 添加背调信息表数据
                 */
                Check check = new Check();
                Long checkId = generateCodeService.getSingleId();
                check.setId(checkId);
                check.setBeSelected(beSelected);
                check.setVerifySource(EnumVerifySource.SF.getCode());
                check.setVerifyUnit(Long.parseLong(cadidate.getStaffId()));
//                check.setVerifyName(cadidate.getStaffName());
                check.insert();

                // 若招聘负责人不存在,则插入
                User user =new User();
                user.setId(Long.parseLong(cadidate.getStaffId()));
//                user.setName(cadidate.getName());
                user.insertOrUpdate();

                // 保存身份信息
                saveCredential(cadidate,beSelected);
                // 保存教育经历
                if (!CollectionUtils.isEmpty(cadidate.getEducations())) {
                    List<EducationBackground> sfEducations = cadidate.getEducations().stream().map(sfEducation -> {
                        EducationBackground result = new EducationBackground();
                        result.setId(generateCodeService.getSingleId());
                        result.setBeSelected(beSelected);
                        result.setCheckId(checkId);
                        result.setSchool(sfEducation.getSchool());
                        result.setSchoolStart(sfEducation.getSchoolStart());
                        result.setGraduationDate(sfEducation.getGraduationDate());
                        result.setMajor(sfEducation.getMajor());
                        result.setEducation(sfEducation.getEducation());
                        result.setEducatioType(sfEducation.getEducationType());
                        result.setDegree(sfEducation.getDegree());
                        return result;
                    }).collect(Collectors.toList());

                    try {
                        educationBackgroundService.saveBatch(sfEducations);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        return ResResult.failed("SF教育背景保存失败!" + e.getMessage());
                    }
                }

                // 保存工作经历列表
                if (!CollectionUtils.isEmpty(cadidate.getExperiences())) {
                    List<Experience> experiences = cadidate.getExperiences().stream().map(sfExperience -> {
                        Experience experience = new Experience();
                        experience.setId(generateCodeService.getSingleId());
                        experience.setBeSelected(beSelected);
                        experience.setCheckId(checkId);
                        experience.setName(sfExperience.getCompanyName());
                        experience.setEntry(sfExperience.getEntryDate());
                        experience.setLeaveDate(sfExperience.getLeaveDate());
//                        experience.setLeaveReason(sfExperience.getLeaveReason());
//                        experience.setMonthSalary(sfExperience.getMonthSalary());
//                        experience.setYearSalary(sfExperience.getYearSalary());
                        experience.setJob(sfExperience.getJob());
//                        experience.setDuty(sfExperience.getDuty());
                        return experience;
                    }).collect(Collectors.toList());

                    try {
                        experienceService.saveBatch(experiences);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        return ResResult.failed("SF工作经历保存失败!" + e.getMessage());
                    }
                }
            }
        }
        return ResResult.success("保存成功!同步数据完成!");
    }


    private Long saveBeSelected(SFCadidate cadidate) {
        Long singleId = generateCodeService.getSingleId();
        BeSelected selectedUser = new BeSelected();
        // id
        selectedUser.setId(singleId);
        // 姓名
//        selectedUser.setName(cadidate.getName());
        // 性别
        selectedUser.setSex(cadidate.getSex());
        // 生日
        selectedUser.setBirthday(DateUtils.stringToDate(cadidate.getBirthday(), "yyyy-MM"));
        // 工作起始时间
//        selectedUser.setWorkDate(cadidate.getWorkStartDate());
        // 手机
        selectedUser.setMobilephone(cadidate.getMobilePhone());
        // 座机
//        selectedUser.setTelephone(cadidate.getTelephone());
        // 电子邮箱
        selectedUser.setMail(cadidate.getMail());
        // 招聘负责人id(同SF系统)
        selectedUser.setRecruitLeader(Long.parseLong(cadidate.getStaffId()));
        // 招聘负责人 员工编号
//        selectedUser.setStaffNumber(cadidate.getStaffNumber());
        // 招聘负责人 员工姓名
//        selectedUser.setStaffName(cadidate.getStaffName());
        // 申请部门
        selectedUser.setDepartment(cadidate.getDepartment());
        // 申请职位
        selectedUser.setPost(cadidate.getPost());
        // 岗位背调类型
//        selectedUser.setPostType(cadidate.getPostType());
        // 员工类型
//        selectedUser.setWorkType(cadidate.getWorkType());
        // 职级
//        selectedUser.setRank(cadidate.getRank());

        try {
            selectedUser.insert();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return singleId;
    }

    private void saveCredential(SFCadidate cadidate, Long beSelected) {
        Credentials credentials = new Credentials();
        credentials.setId(generateCodeService.getSingleId());
        credentials.setBeSelected(beSelected);
//        credentials.setName(cadidate.getName());

        try {
            credentialsService.save(credentials);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

}
