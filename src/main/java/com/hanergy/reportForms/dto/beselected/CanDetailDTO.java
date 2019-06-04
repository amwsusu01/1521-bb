package com.hanergy.reportForms.dto.beselected;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName CandidateDetailDTO
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/21 9:27
 * @Version 1.0
 **/
@ApiModel(value = "候选人详细信息")
public class CanDetailDTO implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "候选人基本信息")
    private CanBasicInfo basicInfo;

    @ApiModelProperty(value = "SF系统或者中招聘人上传的简历信息")
    private CanBasicInfo sfBasicInfo;

    @ApiModelProperty(value = "背景调查进度")
    private String checkProcess;

    @ApiModelProperty(value = "背调信息")
    private List<CheckDTO> checkInfos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CanBasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(CanBasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getCheckProcess() {
        return checkProcess;
    }

    public void setCheckProcess(String checkProcess) {
        this.checkProcess = checkProcess;
    }

    public List<CheckDTO> getCheckInfos() {
        return checkInfos;
    }

    public void setCheckInfos(List<CheckDTO> checkInfos) {
        this.checkInfos = checkInfos;
    }

    public CanBasicInfo getSfBasicInfo() {
        return sfBasicInfo;
    }

    public void setSfBasicInfo(CanBasicInfo sfBasicInfo) {
        this.sfBasicInfo = sfBasicInfo;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
