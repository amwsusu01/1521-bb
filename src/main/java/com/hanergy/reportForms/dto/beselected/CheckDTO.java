package com.hanergy.reportForms.dto.beselected;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.mapper.entity.Credentials;
import com.hanergy.reportForms.mapper.entity.EducationBackground;
import com.hanergy.reportForms.mapper.entity.Experience;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CheckDTO
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/21 10:11
 * @Version 1.0
 **/
@ApiModel(value = "候选人提供信息及对应的背调结果")
public class CheckDTO implements Serializable {

    @ApiModelProperty(value = "背调表主键", example = "1323232323233")
    private Long id;

    @ApiModelProperty(value = "身份验证")
    private Credentials credentials;

    @ApiModelProperty(value = "核实来源：1候选人   2人力    3中介", example = "2")
    private Integer checkSource;

    @ApiModelProperty(value = "背调描述信息", example = "自主背调")
    private String checkDescription;

    @ApiModelProperty("教育背景调查")
    private List<EducationBackground> educations;

    @ApiModelProperty("工作经历调查")
    private List<Experience> experiences;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
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

    public Integer getCheckSource() {
        return checkSource;
    }

    public void setCheckSource(Integer checkSource) {
        this.checkSource = checkSource;
    }

    public String getCheckDescription() {
        return checkDescription;
    }

    public void setCheckDescription(String checkDescription) {
        this.checkDescription = checkDescription;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
