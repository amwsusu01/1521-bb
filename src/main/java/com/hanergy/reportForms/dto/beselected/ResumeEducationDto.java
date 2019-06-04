package com.hanergy.reportForms.dto.beselected;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName ResumeEducationDto
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/8 10:59
 * @Version 1.0
 **/
public class ResumeEducationDto implements Serializable {

    @ApiModelProperty(value = "学校")
    private String school;

    @ApiModelProperty(value = "入学日期")
    private String entryDate;

    @ApiModelProperty(value = "毕业日期")
    private String leaveDate;
    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "学历（最高）：1大专   2本科   3硕士   4博士   0其它")
    private Integer educationType;

    @ApiModelProperty(value = "学历证书编号")
    private String educationNumber;

    @ApiModelProperty(value = "是否授予学位:0-否,1-是")
    private Integer awardDegree;

    @ApiModelProperty(value = "学位")
    private String degree;

    public String getEducationNumber() {
        return educationNumber;
    }

    public void setEducationNumber(String educationNumber) {
        this.educationNumber = educationNumber;
    }

    public Integer getAwardDegree() {
        return awardDegree;
    }

    public void setAwardDegree(Integer awardDegree) {
        this.awardDegree = awardDegree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getEducationType() {
        return educationType;
    }

    public void setEducationType(Integer educationType) {
        this.educationType = educationType;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
