package com.hanergy.reportForms.dto.beselected;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName CanExperienceDTO
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/21 17:22
 * @Version 1.0
 **/
@ApiModel(value = "候选人工作经历", description = "候选人工作经历")
public class CanExperienceDTO implements Serializable {

    @ApiModelProperty(value = "公司名称", notes = "公司名称",example = "人民大学")
    private String companyName;

    @ApiModelProperty(value = "入职时间", example = "2016-10")
    private String entryDate;

    @ApiModelProperty(value = "离职时间", example = "2018-12")
    private String leaveDate;

    @ApiModelProperty(value = "离职原因:1合同到期   2公司辞退   3本人意愿  0其它", example = "1")
    private Integer leaveReason;

    @ApiModelProperty(value = "月薪", example = "20000元")
    private String monthSalary;

    @ApiModelProperty(value = "月薪", example = "200000元")
    private String yearSalary;

    @ApiModelProperty(value = "最终工作职位", example = "Java工程师")
    private String job;

    @ApiModelProperty(value = "工作职责和工作内容", example = "主要职责: 负责代码开发及维护\n\t;项目名称: 公司OA系统,具体负责1,2,3,4")
    private String duty;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(Integer leaveReason) {
        this.leaveReason = leaveReason;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
