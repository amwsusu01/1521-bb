package com.hanergy.reportForms.dto.third;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @ClassName MediCadidate
 * @Description TODO 第三方使用-->> 候选人信息
 * @Author DURONGHONG
 * @DATE 2018/10/9 16:42
 * @Version 1.0
 **/
public class MediCadidate {
    // 候选人id
    private Long id;
    // 姓名
    private String name;
    // 手机
    private String mobilePhone;
    // 电子邮箱
    private String mail;
    // 生日
    private String birthday;
    // 性别
    private String sex;
    // 部门
    private String department;
    // 职位
    private String post;
    // 身份证号
    private String idcard;
    // 证件类型:1:身份证 2:居住证 3:签证 4:护照 5:户口本 6:军人证 7:团员证 8:党员证 9:港澳通行证 0:其他
    private String documentType;
    // 背调负责人ID
    private Long staffId;
    // 背调负责人姓名
    private String staffName="杜荣宏";
    // 背调负责人工号
    private String staffNumber="10050147";
    // 背调负责人邮箱
    private String staffEmail="duronghong@hanergy.com";
    // 背调负责人手机号
    private String staffMobile="15810499088";
    // 教育经历
    private List<MediEducation> educations;
    // 工作经验
    private List<MediExperience> experiences;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public List<MediEducation> getEducations() {
        return educations;
    }

    public void setEducations(List<MediEducation> educations) {
        this.educations = educations;
    }

    public List<MediExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<MediExperience> experiences) {
        this.experiences = experiences;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
