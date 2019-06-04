package com.hanergy.reportForms.dto.beselected;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CandidateBasicInfo
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/21 17:21
 * @Version 1.0
 **/
@ApiModel(value = "候选人基本信息", description = "候选人基本信息")
public class CanBasicInfo implements Serializable {

    @ApiModelProperty(value = "姓名", example = "李小龙或者 Jack Smith")
    private String name;

    @ApiModelProperty(value = "性别", example = "男")
    private String sex;

    @ApiModelProperty(value = "出生日期", example = "1991-10")
    private String birthday;

    @ApiModelProperty(value = "工作日期(毕业后参加工作的起始时间)", example = "2012-09")
    private String workStartDate;

    @ApiModelProperty(value = "手机", example = "13299990000")
    private String mobilePhone;

    @ApiModelProperty(value = "座机电话", example = "010-12345678")
    private String telephone;

    @ApiModelProperty(value = "邮箱", example = "abc@123.com")
    private String mail;

    @ApiModelProperty(value = "年龄", example = "29")
    private Integer age;

    @ApiModelProperty(value = "工作年限", example = "15")
    private Integer workExpirence;

    @ApiModelProperty(value = "招聘负责人id",example = "12")
    private Long recruitLeader;

    @ApiModelProperty(value = "招聘负责人编号", example = "11223333")
    private String staffNumber;

    @ApiModelProperty(value = "招聘负责人姓名", example = "林丹")
    private String staffName;

    @ApiModelProperty(value = "业务负责人id",example = "13")
    private Long recruitLeader2;

    @ApiModelProperty(value = "业务负责人编号",example = "1233223")
    private String staffNumber2;

    @ApiModelProperty(value = "业务负责人姓名",example = "张三")
    private String staffName2;

    @ApiModelProperty(value = "业务负责人邮箱",example = "abc@123.com")
    private String staffEmail2;

    @ApiModelProperty(value = "背调负责人id",example = "123")
    private Long recruitLeader3;

    @ApiModelProperty(value = "背调负责人编号")
    private String staffNumber3;

    @ApiModelProperty(value = "背调负责人姓名")
    private String staffName3;


    @ApiModelProperty(value = "部门（申请部门）", example = "技术研发部")
    private String department;

    @ApiModelProperty(value = "岗位(申请职位)", example = "项目经理")
    private String post;

    @ApiModelProperty(value = "岗位背调类型：1资金相关   0非资金相关   2销售岗   3中睿岗位   4财富(拓丰)岗位", example = "1")
    private Integer postType;

    @ApiModelProperty(value = "员工类型（必填）：1：正式（正式员工必填职级）   2：应届   3：实习", example = "1")
    private Integer workType;

    @ApiModelProperty(value = "职级（1-30级，系统中设置1-30级选项，招聘负责人勾选确认最终职级）", example = "20")
    private Integer rank;

    @ApiModelProperty(value = "背调状态：", example = "0")
    private Integer status;

    @ApiModelProperty(value = "核实等级:1红灯,2黄灯,3蓝灯 4绿灯")
    private Integer verifyLevel;

    @ApiModelProperty(value = "核实状态: 1终版 2 初版")
    private Integer verifyStatus;

    @ApiModelProperty(value = "核实日期")
    private String verifyDate;

    @ApiModelProperty(value = "教育经历列表")
    private List<CanEducationDTO> educations;

    @ApiModelProperty(value = "工作经历列表")
    private List<CanExperienceDTO> experiences;

    @ApiModelProperty(value = "背调明细信息")
    private List<CheckDTO> checkDTOS;

    public List<CheckDTO> getCheckDTOS() {
        return checkDTOS;
    }

    public void setCheckDTOS(List<CheckDTO> checkDTOS) {
        this.checkDTOS = checkDTOS;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorkStartDate() {
        return workStartDate;
    }

    public void setWorkStartDate(String workStartDate) {
        this.workStartDate = workStartDate;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public List<CanEducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<CanEducationDTO> educations) {
        this.educations = educations;
    }

    public List<CanExperienceDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<CanExperienceDTO> experiences) {
        this.experiences = experiences;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWorkExpirence() {
        return workExpirence;
    }

    public void setWorkExpirence(Integer workExpirence) {
        this.workExpirence = workExpirence;
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

    public String getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(String verifyDate) {
        this.verifyDate = verifyDate;
    }
}
