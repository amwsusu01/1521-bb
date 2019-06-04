package com.hanergy.reportForms.VO;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.mapper.entity.Check;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BeSelectedVO
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/27 18:06
 * @Version 1.0
 **/
@ApiModel(value = "被调人详情")
public class BeSelectedVO implements Serializable {


    private static final long serialVersionUID = 756742910804248063L;


    @ApiModelProperty(value = "主键id", example = "1282832838")
    private Long id;
    @ApiModelProperty(value = "姓名", example = "张三")
    private String name;
    @ApiModelProperty(value = "出生日期", example = "1992")
    private String birthday;
    @ApiModelProperty(value = "年龄", example = "12")
    private String age;
    @ApiModelProperty(value = "电子邮箱")
    private String mail;
    @ApiModelProperty(value = "手机号码", example = "13299329399")
    private String mobilePhone;

    @ApiModelProperty(value = "教育经历对应表id,便于修改对应属性", example = "1232323")
    private Long educationId;
    @ApiModelProperty(value = "学校", example = "北京大学")
    private String school;

    @ApiModelProperty(value = "学历（最高）：1大专   2本科   3硕士   4博士   0其它", example = "0")
    private Integer eductaion;

    @ApiModelProperty(value = "学位", example = "学士")
    private String degree;

    @ApiModelProperty(value = "工龄", example = "20")
    private String workExperience;

    @ApiModelProperty(value = "工作日期(毕业后参加工作的起始时间)", example = "2012-09")
    private String workDate;

    @ApiModelProperty(value = "部门(申请部门)", example = "技术研发部")
    private String department;
    @ApiModelProperty(value = "岗位(申请职位)", example = "Java开发")
    private String post;

    @ApiModelProperty(value = "招聘负责人id(背调系统的id)", example = "1291929129")
    private Long recruitLeader;
    @ApiModelProperty(value = "招聘负责人编号", example = "1929129")
    private String staffNumber;
    @ApiModelProperty(value = "招聘负责人姓名", example = "李宁")
    private String staffName;

    @ApiModelProperty(value = "业务负责人id", example = "129129192")
    private Long recruitLeader2;
    @ApiModelProperty(value = "业务负责人编号", example = "2388283")
    private String staffNumber2;
    @ApiModelProperty(value = "业务负责人姓名", example = "王牌")
    private String staffName2;
    @ApiModelProperty(value = "业务负责人邮箱", example = "abc@123.com")
    private String staffEmail2;

    @ApiModelProperty(value = "业务审批结果:1同意 0不同意")
    private Integer staffResult2;
    @ApiModelProperty(value = "业务审批人意见")
    private String staffExplain2;
    @ApiModelProperty(value = "业务审批日期")
    private String staffDate2;

    @ApiModelProperty(value = "背调负责人id(用户表id)", example = "12121212")
    private Long recruitLeader3;
    @ApiModelProperty(value = "背调负责人姓名", example = "里斯")
    private String staffName3;
    @ApiModelProperty(value = "背调负责人编号", example = "1232323")
    private String staffNumber3;
    @ApiModelProperty(value = "背调负责人邮箱")
    private String staffEmail3;
    @ApiModelProperty("背调负责人手机号")
    private String staffMobile3;

    @ApiModelProperty(value = "职级(1-30级，系统中设置1-30级选项，招聘负责人勾选确认最终职级)", example = "12")
    private Integer rank;

    @ApiModelProperty(value = "模板(模板表的主键)", example = "12323")
    private Long template;
    @ApiModelProperty(value = "模板名称", example = "2323")
    private String templateName;
    @ApiModelProperty(value = "审核人员姓名", example = "许你")
    private String verifyPerson;
    @ApiModelProperty(value = "审核说明", example = "就司法局")
    private String verifyExplain;
    @ApiModelProperty(value = "核实等级:1红灯,2黄灯,3蓝灯 4绿灯", example = "2")
    private Integer verifyLevel;
    @ApiModelProperty(value = "核实状态: 1终版 2 初版", example = "2")
    private Integer verifyStatus;
    @ApiModelProperty(value = "核实日期", example = "1992-10-10 11:22:22")
    private String verifyDate;
    @ApiModelProperty(value = "审核人id,对应用户表主键")
    private Long verifyId;

    @ApiModelProperty(value = "背调状态：0未进行   1完成    2背调    3审批")
    private Integer checkStatus;

    @ApiModelProperty(value = "背调的列表")
    private List<Check> checks;

    @ApiModelProperty(value = "按钮顺序,1-保存,2-启动背调,3-发送邮件,4-背调审核")
    private Integer buttonType;

    public Integer getButtonType() {
        return buttonType;
    }

    public void setButtonType(Integer buttonType) {
        this.buttonType = buttonType;
    }

    public Long getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Long verifyId) {
        this.verifyId = verifyId;
    }

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return StringUtils.isEmpty(name) ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getEductaion() {
        return eductaion;
    }

    public void setEductaion(Integer eductaion) {
        this.eductaion = eductaion;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public List<Check> getChecks() {
        return checks;
    }

    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }

    public Long getTemplate() {
        return template;
    }

    public void setTemplate(Long template) {
        this.template = template;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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

    public Integer getVerifyLevel() {
        return verifyLevel;
    }

    public void setVerifyLevel(Integer verifyLevel) {
        this.verifyLevel = verifyLevel;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Long getRecruitLeader() {
        return recruitLeader;
    }

    public void setRecruitLeader(Long recruitLeader) {
        this.recruitLeader = recruitLeader;
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

    public Long getRecruitLeader3() {
        return recruitLeader3;
    }

    public void setRecruitLeader3(Long recruitLeader3) {
        this.recruitLeader3 = recruitLeader3;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getStaffResult2() {
        return staffResult2;
    }

    public void setStaffResult2(Integer staffResult2) {
        this.staffResult2 = staffResult2;
    }

    public String getStaffExplain2() {
        return staffExplain2;
    }

    public void setStaffExplain2(String staffExplain2) {
        this.staffExplain2 = staffExplain2;
    }

    public String getStaffDate2() {
        return staffDate2;
    }

    public void setStaffDate2(String staffDate2) {
        this.staffDate2 = staffDate2;
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

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
