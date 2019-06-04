package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanergy.reportForms.commons.utils.BaseResult;
import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.commons.utils.StrUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 候选人
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@TableName("beselected")
@ApiModel(value = "候选人", description = "候选人")
//@JsonIgnoreProperties(value = {"password"})
public class BeSelected extends Model<BeSelected> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "姓名（候选人）")
    private String name;
    @ApiModelProperty(value = "密码", hidden = true)
    private String password;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "工作日期（毕业后参加工作的起始时间）")
    @TableField("workDate")
    private String workDate;

    @ApiModelProperty(value = "部门（申请部门）")
    private String department;

    @ApiModelProperty(value = "岗位（申请职位）")
    private String post;

    @ApiModelProperty(value = "岗位背调类型：1资金相关   0非资金相关   2销售岗   3中睿岗位   4财富(拓丰)岗位")
    @TableField("postType")
    private Integer postType;

    @ApiModelProperty(value = "员工类型（必填）：1：正式（正式员工必填职级）   2：应届   3：实习")
    @TableField("workType")
    private Integer workType;

    @ApiModelProperty(value = "职级（1-30级，系统中设置1-30级选项，招聘负责人勾选确认最终职级）")
    private Integer rank;

    @ApiModelProperty(value = "职位最大等级")
    private Integer rankMax;

    @ApiModelProperty(value = "作废：1是   0否")
    private Integer cancel;

    @ApiModelProperty(value = "删除：1是   0否")
    private Integer deltag;

    @ApiModelProperty(value = "审核人员id")
    private Long verifyId;

    @ApiModelProperty(value = "招聘负责人id（背调系统的id）")
    @TableField("recruitLeader")
    private Long recruitLeader;

    @ApiModelProperty(value = "招聘负责人编号")
    private String staffNumber;

    @ApiModelProperty(value = "招聘负责人姓名")
    private String staffName;

    @ApiModelProperty(value = "招聘负责人审批结果: 1同意 0不同意")
    private Integer staffResult;
    @ApiModelProperty(value = "招聘负责人审批意见")
    private String staffExplain;

    @ApiModelProperty(value = "业务负责人id")
    @TableField("recruitLeader2")
    private Long recruitLeader2;

    @ApiModelProperty(value = "业务负责人编号")
    private String staffNumber2;

    @ApiModelProperty(value = "业务负责人姓名")
    private String staffName2;

    @ApiModelProperty(value = "业务负责人邮箱")
    private String staffEmail2;

    @ApiModelProperty(value = "业务审批人意见")
    private String staffExplain2;

    @ApiModelProperty(value = "业务审批日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date staffDate2;

    @ApiModelProperty(value = "业务审批结果:1同意 0不同意")
    private Integer staffResult2;

    @ApiModelProperty(value = "背调负责人id(用户表id)")
    private Long recruitLeader3;

    @ApiModelProperty(value = "背调负责人编号")
    private String staffNumber3;

    @ApiModelProperty(value = "背调负责人姓名")
    private String staffName3;
    @ApiModelProperty(value = "背调负责人邮箱")
    private String staffEmail3;
    @ApiModelProperty(value = "背调负责人手机号")
    private String staffMobile3;
    @ApiModelProperty(value = "背调负责人审批意见: 1同意 0不同意")
    private Integer staffResult3;
    @ApiModelProperty(value = "背调负责人审批描述")
    private String staffExplain3;

    @ApiModelProperty(value = "手机")
    private String mobilephone;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "模板（模板表的主键）")
    private Long template;

    @ApiModelProperty(value = "模板名称")
    private String templateName;

    @ApiModelProperty(value = "背调状态：背调状态：0未进行  4授权  5资料填写  6启动（选方案）   2背调中（选公司） 7背调完成待审核    3业务负责人审批  8背调负责人审批  1完成")
    private Integer checkStatus;

    @ApiModelProperty(value = "背调状态修改时间")
    private Date checkStatusTime;

    @ApiModelProperty(value = "自主背调状态  ： 211连机调查      212工作履历")
    private Integer selfCheckStatus;

    @ApiModelProperty(value = "自主背调状态时间")
    private Date selfCheckStatusTime;

    @ApiModelProperty(value = "中介背调状态   ：221待委托         222调查中")
    private Integer agencyCheckStatus;

    @ApiModelProperty(value = "中介背调状态时间")
    private Date agencyCheckStatusTime;
    
    @ApiModelProperty(value = "签署声明状态：  110未签署  111已签署")
    private Integer signStatementStatus;
    
    @ApiModelProperty(value = "签署声明状态时间")
    private Integer signStatementStatusTime;
    
    @ApiModelProperty(value = "入职核查状态：120未进行入职核查   121 已进行入职核查状态")
    private Integer postEntryVerify;
    
    @ApiModelProperty(value = "入职核查状态时间")
    private Integer postEntryVerifyTime;
    
    @ApiModelProperty(value = "审核人员姓名")
    private String verifyPerson;

    @ApiModelProperty(value = "审核说明")
    private String verifyExplain;

    @ApiModelProperty(value = "核实等级:1红灯,2黄灯,3蓝灯 4绿灯")
    private Integer verifyLevel;

    @ApiModelProperty(value = "核实状态: 1终版 2 初版")
    private Integer verifyStatus;


    @ApiModelProperty(value = "核实日期")
    private Date verifyDate;

    @ApiModelProperty(value = "背调授权：0未授权  1授权")
    private Integer authStatus;

    @ApiModelProperty(value = "背调记录")
    @TableField(exist = false)
    private List<Check> checks;


    @ApiModelProperty(value = "身份信息")
    @TableField(exist = false)
    private List<Credentials> credentials;

    @TableField(exist = false)
    @ApiModelProperty(value = "教育背景")
    private List<EducationBackground> educations;

    @TableField(exist = false)
    @ApiModelProperty(value = "工作经历")
    private List<Experience> experiences;

    @ApiModelProperty(value = "专业资格认证列表")
    @TableField(exist = false)
    private List<ProfessinalCertificate> professinalCertificates;

    @ApiModelProperty(value = "法院诉讼")
    @TableField(exist = false)
    private List<CourtProceeds> courtProceeds;

    @ApiModelProperty(value = "商业利益")
    @TableField(exist = false)
    private List<CommercialProfit> commercialProfits;

    @ApiModelProperty(value = "金融违规")
    @TableField(exist = false)
    private List<FinancialIrregularities> financialIrregularities;

    @ApiModelProperty(value = "不良记录")
    @TableField(exist = false)
    private List<BadRecord> badRecords;

    @ApiModelProperty(value = "最高接收教育的学校")
    @TableField(value = "r_school")
    private String rSchool;

    @ApiModelProperty(value = "最高学位")
    @TableField(value = "r_degree")
    private String rDegree;

    @ApiModelProperty(value = "最高学历专业")
    @TableField(value = "r_major")
    private String rMajor;

    @ApiModelProperty(value = "最高学历")
    @TableField(value = "r_education")
    private Integer rEducation;

    @ApiModelProperty(value = "最高学历入学日期")
    @TableField(value = "r_entry")
    private String rEntry;
    @ApiModelProperty(value = "最高学历毕业日期")
    @TableField(value = "r_leave")
    private String rLeave;

    @ApiModelProperty(value = "[冗余字段]身份证号")
    private String rIdcard;

    @TableField(exist = false)
    @ApiModelProperty(value = "年龄")
    private Integer age;

    @TableField(exist = false)
    @ApiModelProperty(value = "工作经验（单位：年）")
    private Integer workTime;


    @ApiModelProperty(value = "HRVP id(用户表Id)")
    private Long hrvpId;

    @ApiModelProperty(value = "HRVP工号")
    private String hrvpNumber;

    @ApiModelProperty(value = "HRVP姓名")
    private String hrvpName;

    @ApiModelProperty(value = "HRVP审批说明")
    private String hrvpExplain;

    @ApiModelProperty(value = "HRVP审批意见: 1同意 0不同意")
    private Integer hrvpVerify;


    @ApiModelProperty(value = "SF系统传递过来的数据,用于与邮箱确定唯一性")
    private String applicationId;

    @ApiModelProperty(value = "[SF数据]职位编号")
    private String positionNumber;

    @ApiModelProperty(value = "[SF数据]投递的部门编号")
    private String departmentCode;

    @ApiModelProperty(value = "[SF数据]简历id")
    private String candidateId;

    @ApiModelProperty(value = "业务负责人审批状态: 1已审批,0待审批")
    private Integer staffStatus2;
    @ApiModelProperty(value = "HRVP审批状态: 1已审批,0待审批")
    private Integer hrvpStatus;
    @ApiModelProperty(value = "背调负责人审批状态: 1已审批,0待审批")
    private Integer staffStatus3;
    @ApiModelProperty(value = "业务负责人审批时间")
    private  Date staffStatusDate2;
    @ApiModelProperty(value = "hrvp审批时间")
    private Date hrvpStatusDate;
    @ApiModelProperty(value = "背调负责人审批时间")
    private Date staffStatusDate3;
    @ApiModelProperty(value = "招聘负责人审批状态: 1已审批,0待审批")
    private Integer staffStatus;
    @ApiModelProperty(value = "招聘负责人审批时间")
    private Date staffStatusDate;

    @ApiModelProperty(value = "通过背调: 1通过,0不通过")
    private Integer pass;

    /**
     * 业务字段,前端使用
     */
    @ApiModelProperty(value = "按钮顺序,1-保存,2-启动背调,3-发送邮件,4-背调审核")
    @TableField(exist = false)
    private Integer buttonType;

    @ApiModelProperty(value = "登陆用户id,统计数据使用,请求接口时必填")
    @TableField(exist = false)
    private Long userId;

    @ApiModelProperty(value = "请求返回信息")
    @TableField(exist = false)
    private BaseResult baseResult;

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(Integer staffStatus) {
        this.staffStatus = staffStatus;
    }

    public Date getStaffStatusDate() {
        return staffStatusDate;
    }

    public void setStaffStatusDate(Date staffStatusDate) {
        this.staffStatusDate = staffStatusDate;
    }

    public BaseResult getBaseResult() {
		return baseResult;
	}

	public void setBaseResult(BaseResult baseResult) {
		this.baseResult = baseResult;
	}
    public Date getStaffStatusDate2() {
        return staffStatusDate2;
    }

    public void setStaffStatusDate2(Date staffStatusDate2) {
        this.staffStatusDate2 = staffStatusDate2;
    }

    public Date getHrvpStatusDate() {
        return hrvpStatusDate;
    }

    public void setHrvpStatusDate(Date hrvpStatusDate) {
        this.hrvpStatusDate = hrvpStatusDate;
    }

    public Date getStaffStatusDate3() {
        return staffStatusDate3;
    }

    public void setStaffStatusDate3(Date staffStatusDate3) {
        this.staffStatusDate3 = staffStatusDate3;
    }

    public Integer getStaffStatus2() {
        return staffStatus2;
    }

    public void setStaffStatus2(Integer staffStatus2) {
        this.staffStatus2 = staffStatus2;
    }

    public Integer getHrvpStatus() {
        return hrvpStatus;
    }

    public void setHrvpStatus(Integer hrvpStatus) {
        this.hrvpStatus = hrvpStatus;
    }

    public Integer getStaffStatus3() {
        return staffStatus3;
    }

    public void setStaffStatus3(Integer staffStatus3) {
        this.staffStatus3 = staffStatus3;
    }

    public Integer getStaffResult() {
        return staffResult;
    }

    public void setStaffResult(Integer staffResult) {
        this.staffResult = staffResult;
    }

    public String getStaffExplain() {
        return staffExplain;
    }

    public void setStaffExplain(String staffExplain) {
        this.staffExplain = staffExplain;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public Integer getStaffResult3() {
        return staffResult3;
    }

    public void setStaffResult3(Integer staffResult3) {
        this.staffResult3 = staffResult3;
    }

    public String getStaffExplain3() {
        return staffExplain3;
    }

    public void setStaffExplain3(String staffExplain3) {
        this.staffExplain3 = staffExplain3;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public Long getHrvpId() {
        return hrvpId;
    }

    public void setHrvpId(Long hrvpId) {
        this.hrvpId = hrvpId;
    }

    public String getHrvpNumber() {
        return hrvpNumber;
    }

    public void setHrvpNumber(String hrvpNumber) {
        this.hrvpNumber = hrvpNumber;
    }

    public String getHrvpName() {
        return hrvpName;
    }

    public void setHrvpName(String hrvpName) {
        this.hrvpName = hrvpName;
    }

    public String getHrvpExplain() {
        return hrvpExplain;
    }

    public void setHrvpExplain(String hrvpExplain) {
        this.hrvpExplain = hrvpExplain;
    }

    public Integer getHrvpVerify() {
        return hrvpVerify;
    }

    public void setHrvpVerify(Integer hrvpVerify) {
        this.hrvpVerify = hrvpVerify;
    }

    public String getrIdcard() {
        return rIdcard;
    }

    public void setrIdcard(String rIdcard) {
        this.rIdcard = rIdcard;
    }

    public Integer getRankMax() {
        return rankMax;
    }

    public void setRankMax(Integer rankMax) {
        this.rankMax = rankMax;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getrMajor() {
        return rMajor;
    }

    public void setrMajor(String rMajor) {
        this.rMajor = rMajor;
    }

    public Integer getrEducation() {
        return rEducation;
    }

    public void setrEducation(Integer rEducation) {
        this.rEducation = rEducation;
    }

    public String getrEntry() {
        return rEntry;
    }

    public void setrEntry(String rEntry) {
        this.rEntry = rEntry;
    }

    public String getrLeave() {
        return rLeave;
    }

    public void setrLeave(String rLeave) {
        this.rLeave = rLeave;
        String rule = DateUtils.PATTER_YYYYMM;
        if(!StrUtil.isEmpty(rLeave)){
        	if(rLeave.trim().length()  == 10  ){
        		rule = DateUtils.PATTERN_YYYYMMDD;
        	}else if (rLeave.trim().length() > 10   ){
        		rule = DateUtils.PATTERN_YYYYMMDDHHMMSS;
        	}

        	this.workTime = DateUtils.defaultCompareTwoDate(DateUtils.stringToDate(rLeave,rule));
        }


    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<CourtProceeds> getCourtProceeds() {
        return courtProceeds;
    }

    public void setCourtProceeds(List<CourtProceeds> courtProceeds) {
        this.courtProceeds = courtProceeds;
    }

    public List<CommercialProfit> getCommercialProfits() {
        return commercialProfits;
    }

    public void setCommercialProfits(List<CommercialProfit> commercialProfits) {
        this.commercialProfits = commercialProfits;
    }

    public List<FinancialIrregularities> getFinancialIrregularities() {
        return financialIrregularities;
    }

    public void setFinancialIrregularities(List<FinancialIrregularities> financialIrregularities) {
        this.financialIrregularities = financialIrregularities;
    }

    public List<BadRecord> getBadRecords() {
        return badRecords;
    }

    public void setBadRecords(List<BadRecord> badRecords) {
        this.badRecords = badRecords;
    }


    public String getrSchool() {
        return rSchool;
    }

    public void setrSchool(String rSchool) {
        this.rSchool = rSchool;
    }

    public String getrDegree() {
        return rDegree;
    }

    public void setrDegree(String rDegree) {
        this.rDegree = rDegree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.age = DateUtils.defaultCompareTwoDate(birthday);
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getRecruitLeader() {
        return recruitLeader;
    }

    public void setRecruitLeader(Long recruitLeader) {
        this.recruitLeader = recruitLeader;
    }


    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getTemplate() {
        return template;
    }

    public void setTemplate(Long template) {
        this.template = template;
    }

    public Date getCheckStatusTime() {
        return checkStatusTime;
    }

    public void setCheckStatusTime(Date checkStatusTime) {
        this.checkStatusTime = checkStatusTime;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getSelfCheckStatus() {
        return selfCheckStatus;
    }

    public void setSelfCheckStatus(Integer selfCheckStatus) {
        this.selfCheckStatus = selfCheckStatus;
    }


    public Date getSelfCheckStatusTime() {
        return selfCheckStatusTime;
    }

    public void setSelfCheckStatusTime(Date selfCheckStatusTime) {
        this.selfCheckStatusTime = selfCheckStatusTime;
    }

    public Integer getAgencyCheckStatus() {
        return agencyCheckStatus;
    }

    public void setAgencyCheckStatus(Integer agencyCheckStatus) {
        this.agencyCheckStatus = agencyCheckStatus;
    }

    public Date getAgencyCheckStatusTime() {
        return agencyCheckStatusTime;
    }

    public void setAgencyCheckStatusTime(Date agencyCheckStatusTime) {
        this.agencyCheckStatusTime = agencyCheckStatusTime;
    }

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

    public Integer getDeltag() {
        return deltag;
    }

    public void setDeltag(Integer deltag) {
        this.deltag = deltag;
    }

    public Long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Long verifyId) {
        this.verifyId = verifyId;
    }

    public String getVerifyPerson() {
        return verifyPerson;
    }

    public void setVerifyPerson(String verifyPerson) {
        this.verifyPerson = verifyPerson;
    }

    public String getVerifyExplain() {
        return verifyExplain;
    }

    public void setVerifyExplain(String verifyExplain) {
        this.verifyExplain = verifyExplain;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getRecruitLeader2() {
        return recruitLeader2;
    }

    public void setRecruitLeader2(Long recruitLeader2) {
        this.recruitLeader2 = recruitLeader2;
    }

    public String getStaffNumber2() {
        return staffNumber2;
    }

    public void setStaffNumber2(String staffNumber2) {
        this.staffNumber2 = staffNumber2;
    }

    public String getStaffName2() {
        return staffName2;
    }

    public void setStaffName2(String staffName2) {
        this.staffName2 = staffName2;
    }

    public String getStaffEmail2() {
        return staffEmail2;
    }

    public void setStaffEmail2(String staffEmail2) {
        this.staffEmail2 = staffEmail2;
    }

    public String getStaffExplain2() {
        return staffExplain2;
    }

    public void setStaffExplain2(String staffExplain2) {
        this.staffExplain2 = staffExplain2;
    }

    public Date getStaffDate2() {
        return staffDate2;
    }

    public void setStaffDate2(Date staffDate2) {
        this.staffDate2 = staffDate2;
    }

    public Integer getStaffResult2() {
        return staffResult2;
    }

    public void setStaffResult2(Integer staffResult2) {
        this.staffResult2 = staffResult2;
    }

    public Long getRecruitLeader3() {
        return recruitLeader3;
    }

    public void setRecruitLeader3(Long recruitLeader3) {
        this.recruitLeader3 = recruitLeader3;
    }

    public String getStaffNumber3() {
        return staffNumber3;
    }

    public void setStaffNumber3(String staffNumber3) {
        this.staffNumber3 = staffNumber3;
    }

    public String getStaffName3() {
        return staffName3;
    }

    public void setStaffName3(String staffName3) {
        this.staffName3 = staffName3;
    }

    public Integer getVerifyLevel() {
        return verifyLevel;
    }

    public void setVerifyLevel(Integer verifyLevel) {
        this.verifyLevel = verifyLevel;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public List<EducationBackground> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationBackground> educations) {
        this.educations = educations;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<Credentials> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credentials> credentials) {
        this.credentials = credentials;
    }

    public List<Check> getChecks() {
        return checks;
    }

    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffEmail3() {
        return staffEmail3;
    }

    public void setStaffEmail3(String staffEmail3) {
        this.staffEmail3 = staffEmail3;
    }

    public String getStaffMobile3() {
        return staffMobile3;
    }

    public void setStaffMobile3(String staffMobile3) {
        this.staffMobile3 = staffMobile3;
    }

    public List<ProfessinalCertificate> getProfessinalCertificates() {
        return professinalCertificates;
    }

    public void setProfessinalCertificates(List<ProfessinalCertificate> professinalCertificates) {
        this.professinalCertificates = professinalCertificates;
    }

    public Integer getButtonType() {
        return buttonType;
    }

    public void setButtonType(Integer buttonType) {
        this.buttonType = buttonType;
    }
    
    public Integer getSignStatementStatus() {
		return signStatementStatus;
	}

	public void setSignStatementStatus(Integer signStatementStatus) {
		this.signStatementStatus = signStatementStatus;
	}

	public Integer getSignStatementStatusTime() {
		return signStatementStatusTime;
	}

	public void setSignStatementStatusTime(Integer signStatementStatusTime) {
		this.signStatementStatusTime = signStatementStatusTime;
	}

	public Integer getPostEntryVerify() {
		return postEntryVerify;
	}

	public void setPostEntryVerify(Integer postEntryVerify) {
		this.postEntryVerify = postEntryVerify;
	}

	public Integer getPostEntryVerifyTime() {
		return postEntryVerifyTime;
	}

	public void setPostEntryVerifyTime(Integer postEntryVerifyTime) {
		this.postEntryVerifyTime = postEntryVerifyTime;
	}

	@Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
