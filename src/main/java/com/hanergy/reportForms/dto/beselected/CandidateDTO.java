package com.hanergy.reportForms.dto.beselected;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName Candidate
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/21 9:14
 * @Version 1.0
 **/
@ApiModel(value = "候选人信息")
public class CandidateDTO implements Serializable {

    private static final long serialVersionUID = -6531254716722813918L;

    @ApiModelProperty(value = "候选人ID", example = "15374284691263114")
    private Long id;

    @ApiModelProperty(value = "候选人姓名", example = "张三")
    private String name;

    @ApiModelProperty(value = "性别", example = "男")
    private String sex;

    @ApiModelProperty(value = "年龄", example = "29")
    private Integer age;

    @ApiModelProperty(value = "生日", example = "1990")
    private String birthday;

    @ApiModelProperty(value = "工作经验", example = "10")
    private String workExpirence;

    @ApiModelProperty(value = "学校", example = "北京大学")
    private String school;

    @ApiModelProperty(value = "学历", example = "本科")
    private String education;

    @ApiModelProperty(value = "电话号码", example = "13299990000")
    private String mobilePhone;

    @ApiModelProperty(value = "电子邮箱", example = "abc@123.com")
    private String mail;

    @ApiModelProperty(value = "应聘职位", example = "高级JAVA")
    private String post;

    @ApiModelProperty(value = "申请部门", example = "全球大数据中心")
    private String department;

    @ApiModelProperty(value = "核实级别", example = "1")
    private Integer checkLevel;

    @ApiModelProperty(value = "简历状态", example = "进行中")
    private Integer status;

    @ApiModelProperty(value = "是否已授权:0-未授权,1-已授权")
    private Integer authStatus;

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getWorkExpirence() {
        return workExpirence;
    }

    public void setWorkExpirence(String workExpirence) {
        this.workExpirence = workExpirence;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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

    public Integer getCheckLevel() {
        return checkLevel;
    }

    public void setCheckLevel(Integer checkLevel) {
        this.checkLevel = checkLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
