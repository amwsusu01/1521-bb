package com.hanergy.reportForms.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanergy.reportForms.commons.enums.*;
import com.hanergy.reportForms.commons.utils.*;
import com.hanergy.reportForms.commons.utils.cont.Constant;
import com.hanergy.reportForms.commons.utils.sfsys.SFUtils;
import com.hanergy.reportForms.dto.BeSelectedNeed;
import com.hanergy.reportForms.dto.third.MediCadidate;
import com.hanergy.reportForms.entity.template.CheckItemEntity;
import com.hanergy.reportForms.entity.template.TemplateInfo;
import com.hanergy.reportForms.entity.template.TemplatePlanEntitiy;
import com.hanergy.reportForms.mapper.*;
import com.hanergy.reportForms.mapper.entity.*;
import com.hanergy.reportForms.service.*;
import com.hanergy.reportForms.service.middle.*;
import com.hanergy.reportForms.service.resumevitae.ResumeVitaeService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>
 * 候选人 服务实现类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class BeSelectedServiceImpl extends ServiceImpl<BeSelectedMapper, BeSelected> implements IBeSelectedService {

    private static Logger logger = LogManager.getLogger(BeSelectedServiceImpl.class);

    @Autowired
    private IExperienceService experienceService;
    @Autowired
    private IEducationBackgroundService educationBackgroundService;
    @Autowired
    private ICredentialsService credentialsService;
    @Autowired
    private ICheckService checkService;
    @Autowired
    private IAgencyService agencyService;
    @Autowired
    private ICheckItemService checkItemService;
    @Autowired
    private IGenerateCodeService generateCodeService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IMailService mailService;
    @Autowired
    private BeSelectedMapper beSelectedMapper;
    @Value("${project.background-system.loginUrl}")
    private String beSelectedLoginUrl;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProfessinalCertificateService professinalCertificateService;
    @Autowired
    private UserRoleMapper userRoleMapper;
    // 法院诉讼
    @Autowired
    private ICourtProceedsService courtProceedsService;
    //
    @Autowired
    private ICommercialProfitService commercialProfitService;
    @Autowired
    private IFinancialIrregularitiesService financialIrregularitiesService;
    @Autowired
    private IBadRecordService badRecordService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private ICheckProjectService checkProjectService;
    @Autowired
    private ICheckService checkInfoService;
    @Autowired
    private AgencyPlanMapper agencyPlanMapper;
    @Value("${project.background-system.beSelectedDetailUrl}")
    private String beSelectedDetailUrl;
    @Autowired
    private IDocumentService documentService;
    @Autowired
    private ResumeVitaeService resumeVitaeService;

    @Autowired
    private CredentialsMapper credentialsMapper;
    @Autowired
    private EducationBackgroundMapper educationBackgroundMapper;
    @Autowired
    private ExperienceMapper experienceMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public BeSelected getBeSelectedInfo(Long beSelected, BeSelectedNeed need) {
        BeSelected entity = this.getById(beSelected);
        if (entity != null) {
            // 1.背调记录
            if (need.getChecks()) {
                QueryWrapper<Check> checkQueryWrapper = new QueryWrapper<>();
                checkQueryWrapper.eq("beSelected", beSelected);
                if (need.getCheckSource() != null) {
                    checkQueryWrapper.eq("verify_source", need.getCheckSource().getCode());
                }
                checkQueryWrapper.orderByAsc("createtime");
                List<Check> checks = checkService.list(checkQueryWrapper);
                if (CollectionUtils.isNotEmpty(checks)) {
                    for (Check check : checks) {
                        QueryWrapper<CheckProject> checkProjectQueryWrapper = new QueryWrapper<>();
                        checkProjectQueryWrapper.eq("check_id", check.getId());
                        List<CheckProject> checkProjects = checkProjectService.list(checkProjectQueryWrapper);
                        check.setCheckProjects(checkProjects);
                        // 背调报告文件列表
                        QueryWrapper<Document> documentQueryWrapper = new QueryWrapper<>();
                        documentQueryWrapper.eq("service_id", check.getId());
                        List<Document> documents = documentService.list(documentQueryWrapper);
                        check.setDocuments(documents);

                    }
                }
                entity.setChecks(checks);
            }

            // 2.身份信息
            if (need.getCredentials()) {
                QueryWrapper<Credentials> wrapper = new QueryWrapper<Credentials>();
                wrapper.eq("beSelected", beSelected);
                if (need.getCredentialsSource() != null) {
                    wrapper.eq("verify_source", need.getCredentialsSource().getCode());
                }
                List<Credentials> credentials = credentialsService.list(wrapper);
                entity.setCredentials(credentials);
            }
            // 3.教育背景
            if (need.getEducation()) {
                QueryWrapper<EducationBackground> educationWrapper = new QueryWrapper<>();
                educationWrapper.eq("beSelected", beSelected);
                educationWrapper.orderByDesc("school_start");
                if (need.getEducationSource() != null) {
                    educationWrapper.eq("verify_source", need.getCredentialsSource().getCode());
                }
                List<EducationBackground> educationBackgrounds = educationBackgroundService.list(educationWrapper);
                entity.setEducations(educationBackgrounds);
            }

            // 4.工作经历
            if (need.getExperience()) {
                QueryWrapper<Experience> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("beSelected", beSelected);
                if (need.getExperienceSource() != null) {
                    queryWrapper.eq("verify_source", need.getCredentialsSource().getCode());
                }
                List<Experience> experiences = experienceService.list(queryWrapper);
                if (CollectionUtils.isNotEmpty(experiences)) {
                    experiences = experiences.stream().peek(experience -> {
                        QueryWrapper<Project> wrapper = new QueryWrapper<>();
                        wrapper.eq("experience", experience.getId());
                        List<Project> projects = projectService.list(wrapper);
                        experience.setProjects(projects);
                    }).collect(Collectors.toList());
                }
//                    experiences.sort(new Comparator<Experience>() {
//                        @Override
//                        public int compare(Experience o1, Experience o2) {
//                            return o2.getEntry().compareTo(o1.getEntry());
//                        }
//                    });
                entity.setExperiences(experiences);
            }

            // 5.专业资格认证列表
            if (need.getProfessinalCertificate()) {
                QueryWrapper<ProfessinalCertificate> certificateQueryWrapper = new QueryWrapper<>();
                certificateQueryWrapper.eq("beSelected", beSelected);
                if (need.getProfessinalCertificateSource() != null) {
                    certificateQueryWrapper.eq("verify_source", need.getCredentialsSource().getCode());
                }
                List<ProfessinalCertificate> professinalCertificates = professinalCertificateService.list(certificateQueryWrapper);
                entity.setProfessinalCertificates(professinalCertificates);
            }

            // 6.法院诉讼
            if (need.getCourtProceeds()) {
                QueryWrapper<CourtProceeds> courtProceedsQueryWrapper = new QueryWrapper<>();
                courtProceedsQueryWrapper.eq("beSelected", beSelected);
                if (need.getCredentialsSource() != null) {
                    courtProceedsQueryWrapper.eq("verify_source", need.getCredentialsSource().getCode());
                }
                List<CourtProceeds> courtProceeds = courtProceedsService.list(courtProceedsQueryWrapper);
                entity.setCourtProceeds(courtProceeds);
            }

            // 7.商业利益
            if (need.getCommercialProfit()) {
                QueryWrapper<CommercialProfit> commercialProfitQueryWrapper = new QueryWrapper<>();
                commercialProfitQueryWrapper.eq("beSelected", beSelected);
                if (need.getCommercialProfitSource() != null) {
                    commercialProfitQueryWrapper.eq("verify_source", need.getCredentialsSource().getCode());
                }
                List<CommercialProfit> commercialProfits = commercialProfitService.list(commercialProfitQueryWrapper);
                entity.setCommercialProfits(commercialProfits);
            }

            // 8.金融违规
            if (need.getFinancial()) {
                QueryWrapper<FinancialIrregularities> financialIrregularitiesQueryWrapper = new QueryWrapper<>();
                financialIrregularitiesQueryWrapper.eq("beSelected", beSelected);
                if (need.getFinancialSource() != null) {
                    financialIrregularitiesQueryWrapper.eq("verify_source", need.getCredentialsSource().getCode());
                }
                List<FinancialIrregularities> financialIrregularities = financialIrregularitiesService.list(financialIrregularitiesQueryWrapper);
                entity.setFinancialIrregularities(financialIrregularities);
            }

            // 9.不良记录
            if (need.getBadRecord()) {
                QueryWrapper<BadRecord> badRecordQueryWrapper = new QueryWrapper<>();
                if (need.getBadRecordSource() != null) {
                    badRecordQueryWrapper.eq("verify_source", need.getCredentialsSource().getCode());
                }
                badRecordQueryWrapper.eq("beSelected", beSelected);
                List<BadRecord> badRecords = badRecordService.list(badRecordQueryWrapper);
                entity.setBadRecords(badRecords);
            }
        }
        return entity;
    }

    @Override
    public JSONObject selctPage(Page<BeSelected> beSelectedPage, QueryWrapper<BeSelected> queryWrapper) {
        IPage<BeSelected> page = this.page(beSelectedPage, queryWrapper);

        return ResResult.success(page);
    }

    @Override
    public JSONObject getTemplateMaps() {

        /**
         * 获取中介列表及所拥有的检查项目
         */
        QueryWrapper<Agency> agencyQueryWrapper = new QueryWrapper<>();
        List<Agency> agencyList = agencyService.list(agencyQueryWrapper);

        if (CollectionUtils.isNotEmpty(agencyList)) {
            for (Agency agency : agencyList) {
                List<CheckItemEntity> itemListByAgencyId = checkItemService.getItemListByAgencyId(agency.getId());
                agency.setCheckItems(itemListByAgencyId);
            }
        }

        return ResResult.success(null, "查询成功");
    }

    @Override
    public JSONObject updateBeselected(BeSelected entity) throws Exception {

        Long beSelectedId = entity.getId();
//        BeSelected selectedById = this.getById(beSelectedId);
        // 防止密码丢失
//        entity.setPassword(selectedById.getPassword());

        // 业务负责人
        if (StringUtils.isNotEmpty(entity.getStaffEmail2())) {
            User user = userService.getOne(new QueryWrapper<User>().eq("mail", entity.getStaffEmail2()));
            if (user == null) {
                user = new User();
                Long userId = generateCodeService.getSingleId();
                user.setId(userId);
                user.setMail(entity.getStaffEmail2());
                user.setJobNumber(entity.getStaffNumber2());
                user.setName(entity.getStaffName2());
                user.insertOrUpdate();

                entity.setRecruitLeader2(userId);

                UserRole userRole = new UserRole();
                userRole.setUser(userId);
                userRole.setRole(EnumRoleType.BUSSINESS.getCode());
                userRoleMapper.saveUserRole(userRole);
            } else {
                entity.setRecruitLeader2(user.getId());
            }
        } else {
            return ResResult.failed("业务负责人工号不可位空!");
        }
        // 模板信息
        // 背调负责人
        if (StringUtils.isNotEmpty(entity.getStaffEmail3())) {
            User user = userService.getOne(new QueryWrapper<User>().eq("mail", entity.getStaffEmail3()));
            if (user == null) {
                user = new User();
                Long userId = generateCodeService.getSingleId();
                user.setId(userId);
                user.setJobNumber(entity.getStaffNumber3());
                user.setName(entity.getStaffName3());
                user.insertOrUpdate();

                entity.setRecruitLeader3(userId);

                UserRole userRole = new UserRole();
                userRole.setUser(userId);
                userRole.setRole(EnumRoleType.BACKGROUND.getCode());
                userRoleMapper.saveUserRole(userRole);
            } else {
                entity.setRecruitLeader3(user.getId());
            }
        }


        /**
         * 教育
         */
        if (CollectionUtils.isNotEmpty(entity.getEducations())) {
            // 删除原有数据
            educationBackgroundService.remove(new UpdateWrapper<EducationBackground>().eq("beSelected", beSelectedId));

            entity.getEducations().sort((o1, o2) -> {
                return o1.getSchoolStart().compareTo(o2.getSchoolStart());
            });

            for (EducationBackground education : entity.getEducations()) {
                if (EnumVerifySource.SF.getCode().equals(education.getVerifySource())) {
                    entity.setrSchool(education.getSchool());
                    entity.setrDegree(education.getDegree());
                    entity.setrEntry(education.getSchoolStart());
                    entity.setrLeave(education.getGraduationDate());
                    entity.setrMajor(education.getMajor());
                    entity.setrEducation(education.getEducation());
                }
                education.insertOrUpdate();
            }
        }
        /**
         * 背调记录不能搞,在启动背调阶段进行背调记录的插入
         */
        /**
         * 身份
         */
        if (CollectionUtils.isNotEmpty(entity.getCredentials())) {
            credentialsService.remove(new UpdateWrapper<Credentials>().eq("beSelected", beSelectedId));
            for (Credentials credentials : entity.getCredentials()) {
                if (EnumVerifySource.CANDIDATE.getCode().equals(credentials.getVerifySource())) {
                    entity.setrIdcard(credentials.getIdcard());
                }
                if (EnumVerifySource.SF.getCode().equals(credentials.getVerifySource())) {
                    credentials.setIdcard(entity.getrIdcard());
                }
                credentials.insertOrUpdate();
            }
        }
        /**
         * 工作经验
         */
        if (CollectionUtils.isNotEmpty(entity.getExperiences())) {
            experienceService.remove(new UpdateWrapper<Experience>().eq("beSelected", beSelectedId));
            for (Experience experience : entity.getExperiences()) {
                /**
                 * 项目经验
                 */
                if (CollectionUtils.isNotEmpty(experience.getProjects())) {
                    projectService.remove(new UpdateWrapper<Project>().eq("experience", experience.getId()));
                    for (Project project : experience.getProjects()) {
                        project.insertOrUpdate();
                    }
                }
                experience.insertOrUpdate();
            }
        }
        /**
         * 专业资格认证
         */
        if (CollectionUtils.isNotEmpty(entity.getProfessinalCertificates())) {
            professinalCertificateService.remove(new UpdateWrapper<ProfessinalCertificate>().eq("beSelected", beSelectedId));
            for (ProfessinalCertificate certificate : entity.getProfessinalCertificates()) {
                certificate.insertOrUpdate();
            }
        }
        /**
         * 法院诉讼
         */
        if (CollectionUtils.isNotEmpty(entity.getCourtProceeds())) {
            courtProceedsService.remove(new UpdateWrapper<CourtProceeds>().eq("beSelected", beSelectedId));
            for (CourtProceeds proceeds : entity.getCourtProceeds()) {
                proceeds.insertOrUpdate();
            }
        }
        /**
         * 商业利益
         */
        if (CollectionUtils.isNotEmpty(entity.getCommercialProfits())) {
            commercialProfitService.remove(new UpdateWrapper<CommercialProfit>().eq("beSelected", beSelectedId));
            for (CommercialProfit profit : entity.getCommercialProfits()) {
                profit.insertOrUpdate();
            }
        }

        /**
         * 金融违规
         */
        if (CollectionUtils.isNotEmpty(entity.getFinancialIrregularities())) {
            financialIrregularitiesService.remove(new UpdateWrapper<FinancialIrregularities>().eq("beSelected", beSelectedId));
            for (FinancialIrregularities financialIrregularities : entity.getFinancialIrregularities()) {
                financialIrregularities.insertOrUpdate();
            }
        }


        /**
         * 不良记录
         */
        if (CollectionUtils.isNotEmpty(entity.getBadRecords())) {
            badRecordService.remove(new QueryWrapper<BadRecord>().eq("beSelected", beSelectedId));
            for (BadRecord badRecord : entity.getBadRecords()) {
                badRecord.insertOrUpdate();
            }
        }

        entity.insertOrUpdate();

        // 其他业务需要
        if (entity.getTemplate() != null) {
            redisService.sAdd(Constant.CANDIDATE_OF_TEMPLATE_RELATION_SET, entity.getTemplate() + "");
        }
        return ResResult.success("修改成功!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject saveResumeForCandidate(BeSelected beselectedInfo) throws Exception {
        Long recruitLeader = beselectedInfo.getRecruitLeader();
        QueryWrapper<Check> checkInfoWrapper = new QueryWrapper<>();
        checkInfoWrapper.eq("beSelected", beselectedInfo.getId());
        checkInfoWrapper.eq("verify_source", EnumVerifySource.SF.getCode());
        Check checkInfo = checkInfoService.getOne(checkInfoWrapper);
        Long checkId = checkInfo.getId();
        if (checkId == null || checkId <= 0) {
            return ResResult.failed("背调方案id错误");
        }
        if (beselectedInfo.getId() == null || beselectedInfo.getId() <= 0) {
            return ResResult.failed("候选人id错误");
        }
        if (beselectedInfo.getAuthStatus() == null || beselectedInfo.getAuthStatus() < 0) {
            return ResResult.failed("候选人授权状态错误");
        }
        if (beselectedInfo.getCheckStatus() != null && beselectedInfo.getCheckStatus() == 5) {
            if (beselectedInfo.getAuthStatus() == 0) {
                return ResResult.failed("候选人未授权");
            }
            if (beselectedInfo.getExperiences() == null || beselectedInfo.getExperiences().size() == 0) {
                return ResResult.failed("候选人工作经历不能为空");
            }
            if (beselectedInfo.getCredentials() == null || beselectedInfo.getCredentials().size() == 0) {
                return ResResult.failed("候选人个人信息不能为空");
            }
            if (beselectedInfo.getEducations() == null || beselectedInfo.getEducations().size() == 0) {
                return ResResult.failed("候选人教育背景不能为空");
            }
        }
        BeSelected beSelected = getById(beselectedInfo.getId());
        //候选人状态发生变化，改变背调统计表，更改保存状态
        return ResResult.success("保存成功!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject startInvest(BeSelected beSelected) throws Exception {
    	return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject complete(BeSelected beSelectedVO) throws Exception {
        beSelectedVO.setCheckStatus(EnumBeSelectedStatus.COMPLETED.getCode());
        beSelectedVO.updateById();
        return ResResult.success("审批成功!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject bussinesOperation(BeSelected beSelected) throws Exception {
        beSelected.setStaffDate2(new Date());
        beSelected.updateById();
        return ResResult.success("业务操作成功!");
    }

    @Override
    public MediCadidate getMediCandidateById(Long beSelected) {
        return beSelectedMapper.getMediCandidateById(beSelected);
    }

    @Override
    public JSONObject sendMailToBussinessLeader(BeSelected beSelected) {

        User user = userService.getById(beSelected.getUserId());
        if (user == null) {
            return ResResult.failed("用户信息不存在!");
        }


        BeSelected entity = getById(beSelected);
        Map<String, Object> model = new HashMap<>();
        model.put("name", entity.getName());
        String url = MessageFormat.format(beSelectedDetailUrl, entity.getRecruitLeader2() + "", entity.getId() + "");
        LogUtils.getPlatformLogger().info("业务负责人审批URL====>>>" + url);
        model.put("url", url);
        try {
            mailService.sendTemplateEmail("[消息通知]候选人背调信息",
                    new String[]{entity.getStaffEmail2()}, "bussinessLeader.ftl",
                    model, null, false);
            return ResResult.success("[业务审批]邮件发送成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResResult.success("发送失败,请重试或联系相关人员解决!");
        }
    }

    /**
     * 获取候选人详细信息及简历信息
     *
     * @param beSelectId
     * @return
     */
    @Override
    public BeSelected getBeSelectedResumeById(Long beSelectId) {
        BeSelected beSelected = getById(beSelectId);
        if (beSelected != null) {

            QueryWrapper<Check> checkQueryWrapper = new QueryWrapper<>();
            checkQueryWrapper.eq("beSelected", beSelectId);
            List<Check> checks = checkService.list(checkQueryWrapper);
            beSelected.setChecks(checks);
            // 工作经历
            QueryWrapper<Experience> experienceWrapper = new QueryWrapper<>();
            experienceWrapper.eq("beSelected", beSelectId);
            experienceWrapper.eq("verify_source", 1);
            experienceWrapper.orderByAsc("entry");
            List<Experience> experiences = experienceService.list(experienceWrapper);
            beSelected.setExperiences(experiences);
            // 教育背景
            QueryWrapper<EducationBackground> educationWrapper = new QueryWrapper<>();
            educationWrapper.eq("beSelected", beSelectId);
            educationWrapper.eq("verify_source", 1);
            educationWrapper.orderByAsc("school_start");
            List<EducationBackground> educationBackgrounds = educationBackgroundService.list(educationWrapper);
            beSelected.setEducations(educationBackgrounds);
            // 身份信息
            QueryWrapper<Credentials> credentialsWrapper = new QueryWrapper<>();
            credentialsWrapper.eq("beSelected", beSelectId);
            credentialsWrapper.eq("verify_source", 1);
            List<Credentials> credentials = credentialsService.list(credentialsWrapper);
            beSelected.setCredentials(credentials);
            //专业资格证
            QueryWrapper<ProfessinalCertificate> professinalWrapper = new QueryWrapper<>();
            professinalWrapper.eq("beSelected", beSelectId);
            professinalWrapper.eq("verify_source", 1);
            List<ProfessinalCertificate> professinalCertificate = professinalCertificateService.list(professinalWrapper);
            beSelected.setProfessinalCertificates(professinalCertificate);
        }
        return beSelected;
    }

    @Override
    public JSONObject getMachineInvestList(Long UserId, String keyword, Integer page, Integer pageSize) {
        int start = page * pageSize;
        int end = (page - 1) * pageSize;
        List<Long> list = new ArrayList<>();
        list.add(EnumCheckItemId.IDENTITY_VERIFICATION.getCode());
        list.add(EnumCheckItemId.BAD_RECORD.getCode());
        list.add(EnumCheckItemId.EDUCATION_VERIFICATION.getCode());
        list.add(EnumCheckItemId.DEGREE_VERIFICATION.getCode());
        list.add(EnumCheckItemId.RESUME_CONTRAST.getCode());
        List<BeSelected> result = beSelectedMapper.getStageListByItemArr(UserId, EnumVerifySource.SF.getCode(), list, EnumVerifyLevel.RED.getCode(), keyword, start, end);
        Integer total = beSelectedMapper.getStageCountByItemArr(UserId, EnumVerifySource.SF.getCode(), list, EnumVerifyLevel.RED.getCode(), keyword);
        JSONObject resResult = ResResult.getResResult();
        resResult.put("status", 1);
        resResult.put("msg", "请求成功!");
        resResult.put("data", result);
        resResult.put("total", total);
        resResult.put("totalPages", (total / page) + 1);
        resResult.put("current", page);
        resResult.put("pageSize", pageSize);
        return resResult;
    }

    @Override
    public JSONObject getWorkExperenceVeriftList(Long UserId, String keyword, Integer page, Integer pageSize) {
        int start = page * pageSize;
        int end = (page - 1) * pageSize;
        List<Long> list = new ArrayList<>();
        list.add(EnumCheckItemId.FIRST_WORK_RECORD_CHECK.getCode());
        list.add(EnumCheckItemId.SECOND_WORK_RECORD_CHECK.getCode());
        list.add(EnumCheckItemId.THIRED_WORK_RECORD_CHECK.getCode());
        List<BeSelected> result = beSelectedMapper.getStageListByItemArr(UserId, EnumVerifySource.SF.getCode(), list, EnumVerifyLevel.RED.getCode(), keyword, start, end);
        Integer total = beSelectedMapper.getStageCountByItemArr(UserId, EnumVerifySource.SF.getCode(), list, EnumVerifyLevel.RED.getCode(), keyword);
        JSONObject resResult = ResResult.getResResult();
        resResult.put("status", 1);
        resResult.put("msg", "请求成功!");
        resResult.put("data", result);
        resResult.put("total", total);
        resResult.put("totalPages", (total / page) + 1);
        resResult.put("current", page);
        resResult.put("pageSize", pageSize);
        return resResult;
    }

    @Override
    public JSONObject updateCheckStatus(Long beSelected, EnumBeSelectedStatus checkStatus) {
        try {
            beSelectedMapper.updateCheckStatus(beSelected, checkStatus.getCode());
            return ResResult.success("修改成功!");
        } catch (Exception e) {
            LogUtils.getExceptionLogger().error(e.getMessage(), e);
            return ResResult.failed("修改失败!");
        }
    }

    @Override
//    @Transactional
    public void sfCandidateToBeSelected(String requestUrl) throws Exception {
        long start = System.currentTimeMillis();
        List<BeSelected> beSelectedList = new ArrayList<>();
        List<Experience> experienceList = new ArrayList<>();
        List<EducationBackground> educationBackgroundList = new ArrayList<>();
        List<Credentials> credentialsList = new ArrayList<>();

        List<User> userList = new ArrayList<>();
        List<UserRole> userRoleList = new ArrayList<>();


        String nextUrl = SFUtils.CANDIDATE_INFO_URL;
        Integer candidateCounts = SFUtils.getCandidateCounts();
        int page = 0;
        Integer pageSize = 500;
        if (candidateCounts != null && candidateCounts > 0) {
            page = candidateCounts % pageSize == 0 ? (candidateCounts / pageSize) : ((candidateCounts / pageSize) + 1);
        }
        nextUrl += "&$top=" + pageSize + "&$skip=";
        AtomicInteger count = new AtomicInteger(0);
        if (page > 0) {
            for (int i = 0; i < page; i++) {
                String url = nextUrl + (1 + pageSize * i);
                logger.info("[定时任务]===>>>从" + ((1 + pageSize * i)) + "到" + (((1 + pageSize * i) + pageSize)));
                String candidateInfos = SFUtils.moreCandidateInfos(url);
                if (StringUtils.isNotEmpty(candidateInfos)) {
                    long total = 0;
                    JSONObject jsonObject = JSONObject.parseObject(candidateInfos);
                    System.out.println("JSON转换消耗时间===>>>>" + (System.currentTimeMillis() - start) + "ms");
                    jsonObject = jsonObject.getJSONObject("d");
                    if (jsonObject != null && jsonObject.size() > 0) {
                        JSONArray results = jsonObject.getJSONArray("results");
//                        nextUrl = jsonObject.getString("__next");
                        logger.info("[定时任务]从SF系统获取候选人===>>>" + url);
                        total = results.size();
                        if (results != null && total > 0) {
                            logger.info("[定时任务]从SF系统获取的候选人数量:" + total);
                            for (int m = 0; m < results.size(); m++) {
                                JSONObject result = results.getJSONObject(m);
                                String applicationId = result.getString("applicationId");
//                                if ("45237".equalsIgnoreCase(applicationId)) {
//                                    logger.info("[定时任务]45237===》》》" + result.toJSONString());
//                                    continue;
//                                }
                                String contactEmail = result.getString("contactEmail");
                                if (StringUtils.isNotEmpty(applicationId)) {
                                    QueryWrapper<BeSelected> beSelectedQueryWrapper = new QueryWrapper<>();
                                    beSelectedQueryWrapper.eq("application_id", applicationId);
//                                    beSelectedQueryWrapper.eq("mail", contactEmail);
                                    List<BeSelected> beSelecteds = baseMapper.selectList(beSelectedQueryWrapper);
                                    if (CollectionUtils.isEmpty(beSelecteds)) {
                                        count.getAndIncrement();

                                        try {

                                            BeSelected beSelected = new BeSelected();
                                            Long serviceSingleId = generateCodeService.getSingleId();
                                            beSelected.setId(serviceSingleId);
                                            // 姓名
                                            String name = result.getString("lastName") + result.getString("firstName");
                                            beSelected.setName(name);
                                            // 邮箱
                                            beSelected.setMail(contactEmail);
                                            // 手机
                                            String cellPhone = result.getString("cellPhone");
                                            beSelected.setMobilephone(cellPhone);
                                            // SF的id
                                            beSelected.setApplicationId(applicationId);
                                            logger.info("[定时任务]开始插入数据,候选人姓名:" + name + ",applicationId=" + applicationId + ",mail=" + contactEmail);
                                            Credentials credentials = new Credentials();
                                            credentials.setVerifySource(EnumVerifySource.SF.getCode());
                                            credentials.setId(generateCodeService.getSingleId());
                                            credentials.setName(name);
                                            credentials.setMobilephone(cellPhone);
                                            // 性别
                                            String gender = result.getString("gender");
                                            if (StringUtils.isNotEmpty(gender)) {
                                                if ("male".equalsIgnoreCase(gender)) {
                                                    beSelected.setSex("男");
                                                    credentials.setSex("男");
                                                } else if ("female".equalsIgnoreCase(gender)) {
                                                    beSelected.setSex("女");
                                                    credentials.setSex("女");
                                                }
                                            }
//                                        credentials.insert();
//                                        credentialsMapper.insert(credentials);
                                            credentialsList.add(credentials);
                                            // 生日
                                            String dateOfBirth = result.getString("dateOfBirth");
                                            if (org.apache.commons.lang3.StringUtils.isNotEmpty(dateOfBirth)) {
                                                beSelected.setBirthday(unixTime(dateOfBirth));
                                            }


                                            // 工作经历
                                            JSONObject outsideWorkExperience = result.getJSONObject("outsideWorkExperience");
                                            if (outsideWorkExperience != null && outsideWorkExperience.size() > 0) {
                                                List<Experience> experiences = new ArrayList<>();
                                                JSONArray workExperienceJSONArray = outsideWorkExperience.getJSONArray("results");
                                                if (workExperienceJSONArray != null && workExperienceJSONArray.size() > 0) {
                                                    IntStream.range(0, workExperienceJSONArray.size()).mapToObj(workExperienceJSONArray::getJSONObject).forEach(object -> {
                                                        Experience experience = new Experience();
                                                        // 设置id
                                                        experience.setId(generateCodeService.getSingleId());
                                                        // 设置候选人id
                                                        experience.setBeSelected(serviceSingleId);
                                                        // 设置数据来源
                                                        experience.setVerifySource(EnumVerifySource.SF.getCode());
                                                        // 公司名称
                                                        experience.setName(object.getString("employer"));
                                                        // 部门
                                                        String businessType = object.getString("businessType");
                                                        logger.info("[定时任务]==>>>候选人：" + applicationId + ",所在部门：" + businessType);
                                                        if (StringUtils.isEmpty(businessType)) {
                                                            businessType = "";
                                                        }
                                                        experience.setDepartment(businessType);
                                                        // 入职时间
                                                        experience.setEntry(DateUtils.defaultDateToString(unixTime(object.getString("startDate"))));
                                                        // 离职时间
                                                        // 有可能出现日期截止错误的可能
                                                        String leaveDate = null;
                                                        String endDate = object.getString("endDate");
                                                        if (StringUtils.isNotEmpty(endDate)) {
                                                            Date date = unixTime(endDate);
                                                            if (date.after(new Date())) {
                                                                leaveDate = null;
                                                            } else {
                                                                leaveDate = DateUtils.defaultDateToString(date);
                                                            }
                                                        }
                                                        experience.setLeaveDate(leaveDate);
                                                        // 职位
                                                        experience.setJob(object.getString("startTitle"));
//                                                    experience.insert();
//                                                    experienceMapper.insert(experience);
                                                        experienceList.add(experience);
                                                    });
                                                }
                                            }

                                            // 求职信息
                                            Long userId = null;
                                            JSONObject jobRequisition = result.getJSONObject("jobRequisition");
                                            if (jobRequisition != null && jobRequisition.size() > 0) {
                                                Integer joblevelmin = jobRequisition.getInteger("joblevelmin");
                                                beSelected.setRank(joblevelmin);
                                                String joblevelmax = jobRequisition.getString("joblevelmax");
                                                if (StringUtils.isNotEmpty(joblevelmax)) {
                                                    if (joblevelmax.contains("L")) {
                                                        joblevelmax = joblevelmax.replace("L", "");
                                                    } else if (joblevelmax.contains("l")) {
                                                        joblevelmax = joblevelmax.replace("l", "");
                                                    }
                                                    beSelected.setRankMax(Integer.parseInt(joblevelmax));
                                                }


                                                beSelected.setPositionNumber(jobRequisition.getString("positionNumber"));
                                                // 投递职位:
                                                JSONObject jobReqLocale = jobRequisition.getJSONObject("jobReqLocale");
                                                if (jobReqLocale != null && jobReqLocale.size() > 0) {
                                                    JSONArray array = jobReqLocale.getJSONArray("results");
                                                    if (array != null && array.size() > 0) {
                                                        IntStream.range(0, array.size()).mapToObj(array::getJSONObject).map(object -> object.getString("jobTitle")).forEach(beSelected::setPost);
                                                    }
                                                }
                                                // 投递部门
                                                JSONObject department_obj = jobRequisition.getJSONObject("department_obj");
                                                if (department_obj != null && department_obj.size() > 0) {
                                                    beSelected.setDepartment(department_obj.getString("description"));
                                                    beSelected.setDepartmentCode(department_obj.getString("externalCode"));
                                                }
                                                // 关联的招聘负责人
                                                JSONObject recruiter = jobRequisition.getJSONObject("recruiter");
                                                if (recruiter != null && recruiter.size() > 0) {
                                                    JSONArray recruiterJSONArray = recruiter.getJSONArray("results");
                                                    for (int j = 0; j < recruiterJSONArray.size(); j++) {
                                                        JSONObject recruiterJSONArrayJSONObject = recruiterJSONArray.getJSONObject(j);
                                                        // 姓
                                                        String lastName = recruiterJSONArrayJSONObject.getString("lastName");
                                                        // 名
                                                        String firstName = recruiterJSONArrayJSONObject.getString("firstName");
                                                        // 邮箱前缀
                                                        String userName = recruiterJSONArrayJSONObject.getString("userName");

                                                        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
                                                        userQueryWrapper.eq("name", lastName + firstName);
                                                        userQueryWrapper.eq("mail", userName.toUpperCase() + "@HANERGY.COM");
                                                        User user = userService.getOne(userQueryWrapper);
                                                        if (user != null) {
                                                            beSelected.setRecruitLeader(user.getId());
                                                            beSelected.setStaffName(lastName + firstName);
                                                            beSelected.setStaffNumber(user.getJobNumber());
                                                            break;
                                                        } else {
                                                            userId = generateCodeService.getSingleId();
                                                            user = new User();
                                                            user.setId(userId);
                                                            user.setJobNumber("");
                                                            user.setName(lastName + firstName);
                                                            user.setMail(userName + "@HANERGY.COM");
                                                            userList.add(user);
//                                                        user.insert();
                                                            userMapper.insert(user);
                                                            // 分配用户
                                                            UserRole userRole = new UserRole();
                                                            userRole.setUser(userId);
                                                            userRole.setRole(EnumRoleType.RECRUIT.getCode());
//                                                        userRole.insert();
//                                                        userRoleMapper.insert(userRole);

                                                            userRoleList.add(userRole);
                                                            beSelected.setRecruitLeader(userId);
                                                            beSelected.setStaffName(lastName + firstName);
                                                            beSelected.setStaffNumber("");
                                                        }
                                                    }
                                                }

                                                // 简历编号:
                                                JSONObject candidate = result.getJSONObject("candidate");
                                                if (candidate != null && candidate.size() > 0) {
                                                    beSelected.setCandidateId(candidate.getString("candidateId"));
                                                }
                                                // 教育经历
                                                JSONObject educationJSONObject = result.getJSONObject("education");
                                                if (educationJSONObject != null && educationJSONObject.size() > 0) {
                                                    List<EducationBackground> educations = new ArrayList<>();
                                                    JSONArray jsonObjectJSONArray = educationJSONObject.getJSONArray("results");
                                                    if (jsonObjectJSONArray != null && jsonObjectJSONArray.size() > 0) {
                                                        // 学院
                                                        IntStream.range(0, jsonObjectJSONArray.size()).mapToObj(jsonObjectJSONArray::getJSONObject).forEachOrdered(object -> {
                                                            EducationBackground entity = new EducationBackground();
                                                            entity.setId(generateCodeService.getSingleId());
                                                            entity.setBeSelected(serviceSingleId);
                                                            entity.setVerifySource(EnumVerifySource.SF.getCode());
                                                            entity.setSchool(object.getString("school"));
                                                            String major = object.getString("major");
                                                            if (StringUtils.isNotEmpty(major)) {
                                                                if (major.contains("/") && !major.contains("N")) {
                                                                    major = redisService.hashGet(Constant.REDIS_SF_MAJOR_MAP, major) + "";
                                                                } else if (major.contains("N/")) {
                                                                    major = major.replace("N/", "");
                                                                } else if (major.contains("未说明")) {
                                                                    major = "";
                                                                }
                                                            } else {
                                                                major = "";
                                                            }

                                                            entity.setMajor(major);
                                                            entity.setSchoolStart(DateUtils.defaultDateToString(unixTime(object.getString("startDate"))));
                                                            entity.setGraduationDate(DateUtils.defaultDateToString(unixTime(object.getString("endDate"))));
                                                            entity.setDegree(degree(object.getInteger("degree")));
                                                            entity.setEducation(educationRecord(object.getInteger("custom6")));
                                                            entity.setCollege(object.getString("custom1"));
//                                                        entity.insert();
//                                                        educationBackgroundMapper.insert(entity);
                                                            educationBackgroundList.add(entity);
                                                            educations.add(entity);
                                                        });
                                                    }
                                                    // 排序教育经历列表,获取最高学历放入到候选人的冗余字段中
                                                    if (CollectionUtils.isNotEmpty(educations)) {
                                                        // 排序获取最高学历
                                                        educations.sort((o1, o2) -> o2.getSchoolStart().compareTo(o1.getSchoolStart()));
                                                        EducationBackground educationBackground = educations.get(0);
                                                        beSelected.setrSchool(educationBackground.getSchool());
                                                        beSelected.setrEntry(educationBackground.getSchoolStart());
                                                        beSelected.setrLeave(educationBackground.getGraduationDate());
                                                        beSelected.setrMajor(educationBackground.getMajor());
                                                        beSelected.setrDegree(educationBackground.getDegree());
                                                    }
                                                }
                                            }
                                            beSelectedList.add(beSelected);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            continue;
                                        }
//                                        baseMapper.insert(beSelected);
                                    }
                                }
                            }
                        }
                        if (CollectionUtils.isNotEmpty(beSelectedList)) {
                            logger.info("[定时任务]===>>>候选人数量:" + beSelectedList.size());
                            int sum = 0;
                            for (BeSelected beSelected : beSelectedList) {
                                beSelected.insert();
                                sum++;
                                if (sum % 500 == 0) {
                                    try {
                                        logger.info("[定时任务]候选人===>>>休息2秒,再插入数据库");
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            beSelectedList.clear();
                        }
                        if (CollectionUtils.isNotEmpty(credentialsList)) {
                            int sum = 0;
                            for (Credentials credentials : credentialsList) {
                                credentials.insert();
                                sum++;
                                if (sum % 500 == 0) {
                                    try {
                                        logger.info("[定时任务]身份信息===>>>休息2秒,再插入数据库");
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            credentialsList.clear();
                        }
                        if (CollectionUtils.isNotEmpty(educationBackgroundList)) {
                            int sum = 0;
                            for (EducationBackground educationBackground : educationBackgroundList) {
                                educationBackground.insert();
                                sum++;
                                if (sum % 500 == 0) {
                                    try {
                                        logger.info("[定时任务]教育背景===>>>休息2秒,再插入数据库");
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            educationBackgroundList.clear();
                        }
                        if (CollectionUtils.isNotEmpty(experienceList)) {
                            int sum = 0;
                            for (Experience experience : experienceList) {
                                experience.insert();
                                sum++;
                                if (sum % 500 == 0) {
                                    try {
                                        logger.info("[定时任务]工作经历===>>>休息2秒,再插入数据库");
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            experienceList.clear();
                        }


                        logger.info("[定时任务]，调取下一页,消耗时间：" + (System.currentTimeMillis() - start) + "ms");
                    }
                }
                Thread.sleep(30000);
            }
        }


        logger.info("[定时任务]插入数据完成,从SF系统实际插入到背调数据库中的数量为:" + count);
    }

    @Override
    public Integer verifyCheckIsOnlySelf(Long beSelected) {

        QueryWrapper<Check> wrapper = new QueryWrapper<>();
        wrapper.eq("beSelected", beSelected);
        List<Check> checks = checkService.list(wrapper);
        if (CollectionUtils.isNotEmpty(checks)) {
            if (checks.size() == 1) {
                Check check = checks.get(0);
                if (EnumVerifySource.SF.getCode().equals(check.getVerifySource())) {
                    return 1;
                } else if (EnumVerifySource.AGENCY.getCode().equals(check.getVerifySource())) {
                    return 2;
                }
            } else {
                return 3;
            }
        }
        return null;
    }

    @Override
    public JSONObject postCheckStatusToSf(Long beSelectedId) {

        JSONObject result = new JSONObject();

        BeSelected beSelected = getById(beSelectedId);
        Map<String, Object> map = new HashMap<>();

        Map<String, String> __metadata = new HashMap<>();
        __metadata.put("uri", "JobApplication('" + beSelected.getApplicationId() + "L')");
        __metadata.put("type", "SFOData.JobApplication");

        map.put("__metadata", __metadata);
        if (beSelected.getCheckStatus() == EnumBeSelectedStatus.COMPLETED.getCode()) {
            map.put("statusId", "219");
        } else {
            map.put("statusId", "218");
        }
        Integer rankMax = beSelected.getRankMax();
        String bResult = "", bReason = "";
        if (rankMax < 15) {
            if (beSelected.getPass() == 1) {
                if (beSelected.getVerifyLevel() == EnumVerifyLevel.GREEN.getCode()) {
                    bResult = "2281";
                } else if (beSelected.getVerifyLevel() == EnumVerifyLevel.BLUE.getCode()) {
                    bResult = "2282";
                } else if (beSelected.getVerifyLevel() == EnumVerifyLevel.YELLOW.getCode()) {
                    bResult = "2283";
                }
            } else if (beSelected.getPass() == 0) {
                bReason = "1922";
                if (beSelected.getVerifyLevel() == EnumVerifyLevel.RED.getCode()) {
                    bResult = "2284";
                } else if (beSelected.getVerifyLevel() == EnumVerifyLevel.BLUE.getCode()) {
                    bResult = "2282";
                } else if (beSelected.getVerifyLevel() == EnumVerifyLevel.YELLOW.getCode()) {
                    bResult = "2283";
                }
            }
            map.put("BackgroundCheckUnder15", bResult);
            map.put("BackgroundResult", "");
        } else {
            map.put("BackgroundCheckUnder15", "");
            map.put("BackgroundResult", bResult);
        }

        map.put("DisqualifyReason", bReason);

        Map<String, String> heads = new HashMap<>();
        heads.put("Content-Type", "application/json;charset=utf-8");

//        HttpTookit.doPost(SFUtils.RETURN_CANDIDATE_BACKGROUND_STATUS_URL,map,heads);
        String sendPostRequest = SFUtils.sendPostRequest(SFUtils.RETURN_CANDIDATE_BACKGROUND_STATUS_URL, JSONObject.toJSONString(map));


        return result;
    }


    private static Date unixTime(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        if (dateStr.contains("+")) {
            dateStr = dateStr.substring(dateStr.indexOf("(") + 1, dateStr.lastIndexOf("+"));
        } else {
            dateStr = dateStr.substring(dateStr.indexOf("(") + 1, dateStr.lastIndexOf(")"));
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(dateStr)) {
            long time = Long.parseLong(dateStr);
            return new Date(time);
        }
        return null;
    }

    /**
     * 学位
     *
     * @param code
     * @return
     */
    private static String degree(Integer code) {
        switch (code) {
            case 580:
                return "无";
            case 581:
                return "双学士";
            case 582:
                return "本科";
            case 583:
                return "硕士";
            case 584:
                return "博士";
            case 585:
                return "名誉博士";
            default:
                return "未说明";
        }
    }

    /**
     * 学历
     *
     * @return
     */
    private static Integer educationRecord(Integer code) {
        switch (code) {
            case 649:           // 高中及以下
                return 5;
            case 650:           // 高等职业学校
                return 6;
            case 651:           // 大学专科
                return 1;
            case 652:           // 大学本科
                return 2;
            case 653:           // 硕士研究生
                return 3;
            case 654:           // 博士研究生
                return 4;
            default:
                return 0;       // 其他
        }
    }
}
