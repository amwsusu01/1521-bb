package com.hanergy.reportForms.dto.sf;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SFCadidate
 * @Description TODO sf系统候选人
 * @Author DURONGHONG
 * @DATE 2018/9/20 11:04
 * @Version 1.0
 **/
@ApiModel(value = "SF系统候选人基本信息", description = "SF系统候选人基本信息")
public class SFCadidate implements Serializable {

    @ApiModelProperty(value = "SF系统简历id")
    private String candidateId;

//    @ApiModelProperty(value = "姓名", example = "李小龙或者 Jack Smith")
//    private String name;

    @ApiModelProperty(value = "名")
    private String firstName;

    @ApiModelProperty(value = "姓")
    private String lastName;

    @ApiModelProperty(value = "性别", example = "男")
    private String sex;

    @ApiModelProperty(value = "出生日期", example = "1991-10")
    private String birthday;

    @ApiModelProperty(value = "手机", example = "13299990000")
    private String mobilePhone;

    @ApiModelProperty(value = "邮箱", example = "abc@123.com")
    private String mail;

    @ApiModelProperty(value = "招聘负责人Id",example = "1234或uuid")
    private String staffId;

    @ApiModelProperty(value = "招聘负责人名")
    private String staffFirstName;
    @ApiModelProperty(value = "招聘负责人姓")
    private String staffLastName;

    @ApiModelProperty(value = "申请的部门编号")
    private String departmentNumber;

    @ApiModelProperty(value = "部门（申请部门）", example = "技术研发部")
    private String department;

    @ApiModelProperty(value = "职位编号")
    private String postNumber;

    @ApiModelProperty(value = "岗位(申请职位)", example = "项目经理")
    private String post;

    @ApiModelProperty(value = "职级范围下限")
    private String rankRangeMin;

    @ApiModelProperty(value = "职级范围上限")
    private String rankRangeMax;

    @ApiModelProperty(value = "是否在汉能工作过")
    private String stayAtHanergy;

    @ApiModelProperty(value = "教育经历列表")
    private List<SFEducation> educations;

    @ApiModelProperty(value = "工作经历列表")
    private List<SFExperience> experiences;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public List<SFEducation> getEducations() {
        return educations;
    }

    public void setEducations(List<SFEducation> educations) {
        this.educations = educations;
    }

    public List<SFExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<SFExperience> experiences) {
        this.experiences = experiences;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(String postNumber) {
        this.postNumber = postNumber;
    }

    public String getRankRangeMin() {
        return rankRangeMin;
    }

    public void setRankRangeMin(String rankRangeMin) {
        this.rankRangeMin = rankRangeMin;
    }

    public String getRankRangeMax() {
        return rankRangeMax;
    }

    public void setRankRangeMax(String rankRangeMax) {
        this.rankRangeMax = rankRangeMax;
    }

    public String getStayAtHanergy() {
        return stayAtHanergy;
    }

    public void setStayAtHanergy(String stayAtHanergy) {
        this.stayAtHanergy = stayAtHanergy;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
