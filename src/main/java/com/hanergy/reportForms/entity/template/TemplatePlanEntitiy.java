package com.hanergy.reportForms.entity.template;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.mapper.entity.Agency;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class TemplatePlanEntitiy implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "渠道方名称")
    private String agencyName;

    @ApiModelProperty(value = "渠道方id")
    private Long agencyId;

    @ApiModelProperty(value = "中介模板id")
    private Long agencyPlanId;

    @ApiModelProperty(value = "中介类型：0招聘负责人自主背调；1中介背调")
    private Integer agencyType;

    @ApiModelProperty(value = "检查项数组")
    private List<CheckItemEntity> itemList;
    
    @ApiModelProperty(value = "匹配的中介公司列表")
    private List<Agency> agencies;

    public List<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public List<CheckItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CheckItemEntity> itemList) {
        this.itemList = itemList;
    }

    public Integer getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(Integer agencyType) {
        this.agencyType = agencyType;
    }

    public Long getAgencyPlanId() {
        return agencyPlanId;
    }

    public void setAgencyPlanId(Long agencyPlanId) {
        this.agencyPlanId = agencyPlanId;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
