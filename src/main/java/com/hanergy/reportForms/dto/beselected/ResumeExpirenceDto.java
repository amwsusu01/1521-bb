package com.hanergy.reportForms.dto.beselected;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName ResumeExpirenceDto
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/8 11:01
 * @Version 1.0
 **/
@ApiModel(value = "候选人工作经验")
public class ResumeExpirenceDto implements Serializable {

    @ApiModelProperty(value = "公司名称")
    private String company;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "职位")
    private String job;

    @ApiModelProperty(value = "入职时间")
    private String entryDate;

    @ApiModelProperty(value = "离职时间")
    private String leaveDate;

    @ApiModelProperty(value = "月薪")
    private String monthSalary;

    @ApiModelProperty(value = "年薪")
    private String yearSalary;

    @ApiModelProperty(value = "工作内容")
    private String duty;

    @ApiModelProperty(value = "履历受访人/HR")
    private String visitor;

    @ApiModelProperty(value = "履历受访人手机")
    private String vistorMobile;

    @ApiModelProperty(value = "履历受访人职务")
    private String visitorJob;

    @ApiModelProperty(value = "履历受访人关系", example = "上级")
    private String visitorRelation;

    @ApiModelProperty(value = "表现受访人/主管上级")
    private String visitor2;

    @ApiModelProperty(value = "表现受访人手机")
    private String visitorMobile2;

    @ApiModelProperty(value = "表现受访人职务")
    private String visitorJob2;

    @ApiModelProperty(value = "表现受访人关系")
    private String visitorRelation2;

    @ApiModelProperty(value = "具体工作地址")
    private String address;
    @ApiModelProperty(value = "业绩表现描述")
    private String performanceDesc;
    @ApiModelProperty(value = "公司电话")
    private String telephone;
    @ApiModelProperty(value = "可否与单位核实:0:否,1:是")
    private Integer canVerfify;

    @ApiModelProperty(value = "可与该单位核实的时间")
    private String canVerifyDate;

    @ApiModelProperty(value = "同事姓名")
    private String visitor3;

    @ApiModelProperty(value = "同事职位")
    private String visitorJob3;
    @ApiModelProperty(value = "同事电话")
    private String visitorPhone3;
    @ApiModelProperty(value = "同事邮箱")
    private String visitorMail3;
    @ApiModelProperty(value = "表现受访人邮箱")
    private String visitorEmail2;
    @ApiModelProperty(value = "履历受访人邮箱")
    private String visitorMail;
    @ApiModelProperty(value = "工作方式:1-实习,2-兼职,3-全职正式,4-尚在试用期,5-第三方派遣")
    private Integer workMode;


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(String monthSalary) {
        this.monthSalary = monthSalary;
    }

    public String getYearSalary() {
        return yearSalary;
    }

    public void setYearSalary(String yearSalary) {
        this.yearSalary = yearSalary;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getVistorMobile() {
        return vistorMobile;
    }

    public void setVistorMobile(String vistorMobile) {
        this.vistorMobile = vistorMobile;
    }

    public String getVisitorJob() {
        return visitorJob;
    }

    public void setVisitorJob(String visitorJob) {
        this.visitorJob = visitorJob;
    }

    public String getVisitorRelation() {
        return visitorRelation;
    }

    public void setVisitorRelation(String visitorRelation) {
        this.visitorRelation = visitorRelation;
    }

    public String getVisitor2() {
        return visitor2;
    }

    public void setVisitor2(String visitor2) {
        this.visitor2 = visitor2;
    }

    public String getVisitorMobile2() {
        return visitorMobile2;
    }

    public void setVisitorMobile2(String visitorMobile2) {
        this.visitorMobile2 = visitorMobile2;
    }

    public String getVisitorJob2() {
        return visitorJob2;
    }

    public void setVisitorJob2(String visitorJob2) {
        this.visitorJob2 = visitorJob2;
    }

    public String getVisitorRelation2() {
        return visitorRelation2;
    }

    public void setVisitorRelation2(String visitorRelation2) {
        this.visitorRelation2 = visitorRelation2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerformanceDesc() {
        return performanceDesc;
    }

    public void setPerformanceDesc(String performanceDesc) {
        this.performanceDesc = performanceDesc;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getCanVerfify() {
        return canVerfify;
    }

    public void setCanVerfify(Integer canVerfify) {
        this.canVerfify = canVerfify;
    }

    public String getCanVerifyDate() {
        return canVerifyDate;
    }

    public void setCanVerifyDate(String canVerifyDate) {
        this.canVerifyDate = canVerifyDate;
    }

    public String getVisitor3() {
        return visitor3;
    }

    public void setVisitor3(String visitor3) {
        this.visitor3 = visitor3;
    }

    public String getVisitorJob3() {
        return visitorJob3;
    }

    public void setVisitorJob3(String visitorJob3) {
        this.visitorJob3 = visitorJob3;
    }

    public String getVisitorPhone3() {
		return visitorPhone3;
	}

	public void setVisitorPhone3(String visitorPhone3) {
		this.visitorPhone3 = visitorPhone3;
	}

	public String getVisitorMail3() {
        return visitorMail3;
    }

    public void setVisitorMail3(String visitorMail3) {
        this.visitorMail3 = visitorMail3;
    }

    public String getVisitorEmail2() {
        return visitorEmail2;
    }

    public void setVisitorEmail2(String visitorEmail2) {
        this.visitorEmail2 = visitorEmail2;
    }

    public String getVisitorMail() {
        return visitorMail;
    }

    public void setVisitorMail(String visitorMail) {
        this.visitorMail = visitorMail;
    }

    public Integer getWorkMode() {
        return workMode;
    }

    public void setWorkMode(Integer workMode) {
        this.workMode = workMode;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
