package com.hanergy.reportForms.service.resumevitae;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hanergy.reportForms.commons.enums.EnumCheckItemId;
import com.hanergy.reportForms.commons.enums.EnumVerifySource;
import com.hanergy.reportForms.mapper.entity.BeSelected;
import com.hanergy.reportForms.mapper.entity.Check;
import com.hanergy.reportForms.mapper.entity.CheckProject;
import com.hanergy.reportForms.mapper.entity.Credentials;
import com.hanergy.reportForms.mapper.entity.EducationBackground;
import com.hanergy.reportForms.service.ICheckProjectService;
import com.hanergy.reportForms.service.ICheckService;
import com.hanergy.reportForms.service.ICredentialsService;
import com.hanergy.reportForms.service.IEducationBackgroundService;
import com.hanergy.reportForms.service.IGenerateCodeService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeVitaeServiceImpl implements ResumeVitaeService {

    @Autowired
    private IEducationBackgroundService educationBackgroundService;
    @Autowired
    private IGenerateCodeService generateCodeService;
    @Autowired
    private ICredentialsService credentialsService;
    @Autowired
    private ICheckService checkInfoService;
    @Autowired
    private ICheckProjectService checkProjectService;
    @Override
    public BeSelected getResumeVitaeResult(BeSelected beSelected) {
    	//简历对比核实说明
    	String JLDBSM = "简历对比";
    	//简历对比核实内容
    	String JLDBHSNR ="";
    	Boolean eduflag = true ;
    	//用户自主背调对象
        QueryWrapper<Check> checkQueryWrapper = new QueryWrapper<>();
        checkQueryWrapper.eq("beSelected", beSelected.getId());
        checkQueryWrapper.eq("verify_source", EnumVerifySource.SF.getCode());
        Check checkInfo = checkInfoService.getOne(checkQueryWrapper);
        
        QueryWrapper<CheckProject> checkProjectQueryWrapper = new QueryWrapper<>();
        checkProjectQueryWrapper.eq("check_id", checkInfo.getId());
        checkProjectQueryWrapper.eq("verify_source", EnumVerifySource.SF.getCode());
        checkProjectQueryWrapper.eq("checkitem_id", EnumCheckItemId.EDUCATION_VERIFICATION.getCode());
        checkProjectService.remove(checkProjectQueryWrapper);
        
        QueryWrapper<CheckProject> checkProjectQueryWrapper1 = new QueryWrapper<>();
        checkProjectQueryWrapper1.eq("check_id", checkInfo.getId());
        checkProjectQueryWrapper1.eq("verify_source", EnumVerifySource.SF.getCode());
        checkProjectQueryWrapper1.eq("checkitem_id", EnumCheckItemId.DEGREE_VERIFICATION.getCode());
        checkProjectService.remove(checkProjectQueryWrapper1);
        
        CheckProject checkProject = new CheckProject();
        checkProject.setCheckId(checkInfo.getId());
        checkProject.setVerifySource(EnumVerifySource.SF.getCode());
    	checkProject.setCheckitemId(EnumCheckItemId.DEGREE_VERIFICATION.getCode());
        checkProject.setId(generateCodeService.getSingleId());
        checkProject.setVerifyExplain(JLDBSM);
        
        List<EducationBackground> educationBackgroundList = beSelected.getEducations();
        EducationBackground educationBackgroundvs1 = null;
        EducationBackground educationBackgroundvs2 = null;
        for (int i = 0; i < educationBackgroundList.size(); i++) {
            if ("1".equals(educationBackgroundList.get(i).getVerifySource().toString())) {
                educationBackgroundvs1 = educationBackgroundList.get(i);
            }
            if ("2".equals(educationBackgroundList.get(i).getVerifySource().toString())) {
                educationBackgroundvs2 = educationBackgroundList.get(i);
            }
        }
        if (educationBackgroundvs1 != null) {
        	if( educationBackgroundvs2 != null){
        		//学校名称
                if(StringUtils.isNotBlank(educationBackgroundvs1.getSchool())&&StringUtils.isNotBlank(educationBackgroundvs2.getSchool())){
                    if (educationBackgroundvs1.getSchool().equals(educationBackgroundvs2.getSchool())) {
                        educationBackgroundvs1.setSchoolVerify(1);
                    	educationBackgroundvs2.setSchoolVerify(1);
                    	educationBackgroundvs1.setSchoolVerifyExplain("一致");
                    	educationBackgroundvs2.setSchoolVerifyExplain("一致");
                    } else {
                        educationBackgroundvs1.setSchoolVerify(0);
                        educationBackgroundvs2.setSchoolVerify(0);
                    	educationBackgroundvs1.setSchoolVerifyExplain("不一致");
                    	educationBackgroundvs2.setSchoolVerifyExplain("不一致");
                    	eduflag = false ;
                    	JLDBHSNR = JLDBHSNR+ " 学校名称不一致 ;"; 
                    }
                }
                //学院名称
                if(StringUtils.isNotBlank(educationBackgroundvs1.getCollege())&&StringUtils.isNotBlank(educationBackgroundvs2.getCollege())){
                    if (educationBackgroundvs1.getCollege().equals(educationBackgroundvs2.getCollege())) {
                        educationBackgroundvs1.setCollegeVerify(1);
                    	educationBackgroundvs2.setCollegeVerify(1);
                    	educationBackgroundvs1.setCollegeVerifyExplain("一致");
                    	educationBackgroundvs2.setCollegeVerifyExplain("一致");
                    } else {
                        educationBackgroundvs1.setCollegeVerify(0);
                        educationBackgroundvs2.setCollegeVerify(0);
                    	educationBackgroundvs1.setCollegeVerifyExplain("不一致");
                    	educationBackgroundvs2.setCollegeVerifyExplain("不一致");
                    	eduflag = false ;
                    	JLDBHSNR = JLDBHSNR+ " 学院名称不一致 ;"; 
                    }
                }
                //入学日期
                if(StringUtils.isNotBlank(educationBackgroundvs1.getSchoolStart())&&StringUtils.isNotBlank(educationBackgroundvs2.getSchoolStart())){
                    if (educationBackgroundvs1.getSchool().equals(educationBackgroundvs2.getSchool())) {
                        educationBackgroundvs1.setSchoolStartVerify(1);
                    	educationBackgroundvs2.setSchoolStartVerify(1);
                    	educationBackgroundvs1.setSchoolStartVerifyExplain("一致");
                    	educationBackgroundvs2.setSchoolStartVerifyExplain("一致");
                    } else {
                        educationBackgroundvs1.setSchoolStartVerify(0);
                        educationBackgroundvs2.setSchoolStartVerify(0);
                    	educationBackgroundvs1.setSchoolStartVerifyExplain("不一致");
                    	educationBackgroundvs2.setSchoolStartVerifyExplain("不一致");
                    	eduflag = false ;
                    	JLDBHSNR = JLDBHSNR+ " 入学日期不一致 ;"; 
                    }
                }
                //毕业日期
                if(StringUtils.isNotBlank(educationBackgroundvs1.getGraduationDate())&&StringUtils.isNotBlank(educationBackgroundvs2.getGraduationDate())){
                    if (educationBackgroundvs1.getGraduationDate().equals(educationBackgroundvs2.getGraduationDate())) {
                        educationBackgroundvs1.setGraduationDateVerify(1);
                    	educationBackgroundvs2.setGraduationDateVerify(1);
                    	educationBackgroundvs1.setGraduationDateVerifyExplain("一致");
                    	educationBackgroundvs2.setGraduationDateVerifyExplain("一致");
                    } else {
                        educationBackgroundvs1.setGraduationDateVerify(0);
                        educationBackgroundvs2.setGraduationDateVerify(0);
                    	educationBackgroundvs1.setGraduationDateVerifyExplain("不一致");
                    	educationBackgroundvs2.setGraduationDateVerifyExplain("不一致");
                    	eduflag = false ;
                    	JLDBHSNR = JLDBHSNR+ " 毕业日期不一致 ;"; 
                    }
                }
                //专业
                if(StringUtils.isNotBlank(educationBackgroundvs1.getMajor())&& StringUtils.isNotBlank(educationBackgroundvs2.getMajor())){
                    if (educationBackgroundvs1.getMajor().equals(educationBackgroundvs2.getMajor())) {
                        educationBackgroundvs1.setMajorVerify(1);
                    	educationBackgroundvs2.setMajorVerify(1);
                    	educationBackgroundvs1.setMajorVerifyExplain("一致");
                    	educationBackgroundvs2.setMajorVerifyExplain("一致");
                    } else {
                        educationBackgroundvs1.setMajorVerify(0);
                        educationBackgroundvs2.setMajorVerify(0);
                    	educationBackgroundvs1.setMajorVerifyExplain("不一致");
                    	educationBackgroundvs2.setMajorVerifyExplain("不一致");
                    	eduflag = false ;
                    	JLDBHSNR = JLDBHSNR+ " 专业不一致 ;"; 
                    }
                }
                //学历
                if(educationBackgroundvs1.getEducation()!=null && educationBackgroundvs2.getEducation()!=null ){
                    if (educationBackgroundvs1.getEducation() == educationBackgroundvs2.getEducation()) {
                        educationBackgroundvs1.setEducationVerify(1);
                    	educationBackgroundvs2.setEducationVerify(1);
                    	educationBackgroundvs1.setEducationVerifyExplain("一致");
                    	educationBackgroundvs2.setEducationVerifyExplain("一致");
                    } else {
                        educationBackgroundvs1.setEducationVerify(0);
                        educationBackgroundvs2.setEducationVerify(0);
                    	educationBackgroundvs1.setEducationVerifyExplain("不一致");
                    	educationBackgroundvs2.setEducationVerifyExplain("不一致");
                    	eduflag = false ;
                    	JLDBHSNR = JLDBHSNR+ " 学历不一致 ;"; 
                    }
                }
                //学位
                if(StringUtils.isNotBlank(educationBackgroundvs1.getDegree())&&StringUtils.isNotBlank(educationBackgroundvs2.getDegree())){
                    if (educationBackgroundvs1.getDegree().equals(educationBackgroundvs2.getDegree())) {
                        educationBackgroundvs1.setDegreeVerify(1);
                    	educationBackgroundvs2.setDegreeVerify(1);
                    	educationBackgroundvs1.setDegreeVerifyExplain("一致");
                    	educationBackgroundvs2.setDegreeVerifyExplain("一致");
                    } else {
                        educationBackgroundvs1.setDegreeVerify(0);
                        educationBackgroundvs2.setDegreeVerify(0);
                    	educationBackgroundvs1.setDegreeVerifyExplain("不一致");
                    	educationBackgroundvs2.setDegreeVerifyExplain("不一致");
                    	eduflag = false ;
                    	JLDBHSNR = JLDBHSNR+ " 学位不一致 ;"; 
                    }
                }
                educationBackgroundService.removeById(educationBackgroundvs1.getId());
                educationBackgroundService.removeById(educationBackgroundvs2.getId());
                educationBackgroundvs1.setId(generateCodeService.getSingleId());
                educationBackgroundvs2.setId(generateCodeService.getSingleId());
                educationBackgroundvs1.insert();
                educationBackgroundvs2.insert();
            	if(eduflag){
                	//简历对比结果一致
                    checkProject.setVerifyContent("内容一致");
                    checkProject.setVerifyLevel(4);
                }else{
                	//简历对比结果不一致
                    checkProject.setVerifyContent(JLDBHSNR);
                    checkProject.setVerifyLevel(1);
                }
        	}else{
                checkProject.setVerifyContent("SF教育背景无内容");
                checkProject.setVerifyLevel(1);
        	}
        	checkProject.insert();
        	//页面有问题  学历报告复制一份到学位
        	checkProject.setId(generateCodeService.getSingleId());
        	checkProject.setCheckitemId(EnumCheckItemId.EDUCATION_VERIFICATION.getCode());
        	checkProject.insert();
        }
        //身份信息对比
        List<Credentials> credentialsList = beSelected.getCredentials();
        Credentials credential1 = null;
        Credentials credential2 = null;
        for (int i = 0; i < credentialsList.size(); i++) {
            if ("1".equals(credentialsList.get(i).getVerifySource().toString())) {
            	credential1 = credentialsList.get(i);
            }
            if ("2".equals(credentialsList.get(i).getVerifySource().toString())) {
            	credential2 = credentialsList.get(i);
            }
        }
		if (credential1 != null) {
			if (credential2 != null) {
                //姓名
                if(StringUtils.isNotBlank(credential1.getName())&&StringUtils.isNotBlank(credential2.getName())){
                    if (credential1.getName().equals(credential2.getName())) {
                        credential1.setNameVerify(1);
                    	credential2.setNameVerify(1);
                    	credential1.setNameVerifyExplain("一致");
                    	credential2.setNameVerifyExplain("一致");
                    } else {
                        credential1.setNameVerify(0);
                        credential2.setNameVerify(0);
                    	credential1.setNameVerifyExplain("不一致");
                    	credential2.setNameVerifyExplain("不一致");
                    }
                }
                //身份证
                if(StringUtils.isNotBlank(credential1.getIdcard())&&StringUtils.isNotBlank(credential2.getIdcard())){
                    if (credential1.getIdcard().equals(credential2.getIdcard())) {
                        credential1.setIdcardVerify(1);
                    	credential2.setIdcardVerify(1);
                    	credential1.setIdcardVerfifyExplain("一致");
                    	credential2.setIdcardVerfifyExplain("一致");
                    } else {
                        credential1.setIdcardVerify(0);
                        credential2.setIdcardVerify(0);
                    	credential1.setIdcardVerfifyExplain("不一致");
                    	credential2.setIdcardVerfifyExplain("不一致");
                    }
                }
                //民族
                if(StringUtils.isNotBlank(credential1.getNation())&&StringUtils.isNotBlank(credential2.getNation())){
                    if (credential1.getNation().equals(credential2.getNation())) {
                        credential1.setNationVerify(1);
                    	credential2.setNationVerify(1);
                    	credential1.setNationVerifyExplain("一致");
                    	credential2.setNationVerifyExplain("一致");
                    } else {
                        credential1.setNationVerify(0);
                        credential2.setNationVerify(0);
                    	credential1.setNationVerifyExplain("不一致");
                    	credential2.setNationVerifyExplain("不一致");
                    }
                }
                //户籍地
                if(StringUtils.isNotBlank(credential1.getDomPlace())&&StringUtils.isNotBlank(credential2.getDomPlace())){
                    if (credential1.getDomPlace().equals(credential2.getDomPlace())) {
                        credential1.setDomPlaceVerify(1);
                    	credential2.setDomPlaceVerify(1);
                    	credential1.setDomPlaceVerifyExplain("一致");
                    	credential2.setDomPlaceVerifyExplain("一致");
                    } else {
                        credential1.setDomPlaceVerify(0);
                        credential2.setDomPlaceVerify(0);
                    	credential1.setDomPlaceVerifyExplain("不一致");
                    	credential2.setDomPlaceVerifyExplain("不一致");
                    }
                }
                credentialsService.removeById(credential1.getId());
                credentialsService.removeById(credential2.getId());
                credential1.setId(generateCodeService.getSingleId());
                credential2.setId(generateCodeService.getSingleId());
                credential1.insert();
                credential2.insert();
			}
		}
        return beSelected;
    }

}
