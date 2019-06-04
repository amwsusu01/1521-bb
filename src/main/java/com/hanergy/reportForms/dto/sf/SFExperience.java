package com.hanergy.reportForms.dto.sf;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName SFExperience
 * @Description TODO sf系统候选人经历
 * @Author DURONGHONG
 * @DATE 2018/9/20 11:04
 * @Version 1.0
 **/
@ApiModel(value = "SF系统候选人工作经历", description = "SF系统候选人工作经历")
public class SFExperience implements Serializable {

    @ApiModelProperty(value = "公司名称", notes = "公司名称",example = "人民大学")
    private String companyName;

    @ApiModelProperty(value = "入职时间", example = "2016-10")
    private String entryDate;

    @ApiModelProperty(value = "离职时间", example = "2018-12")
    private String leaveDate;

    @ApiModelProperty(value = "最终工作职位", example = "Java工程师")
    private String job;

    @ApiModelProperty(value = "所在部门")
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

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


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
