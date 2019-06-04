package com.hanergy.reportForms.entity.entrust.report;

import java.io.Serializable;
import java.util.List;

import com.hanergy.reportForms.entity.template.CheckItemEntity;

import io.swagger.annotations.ApiModelProperty;

public class TemplatePlanInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "渠道方名称")
	private String agencyName;
    
    @ApiModelProperty(value = "渠道方id")
	private Long agencyId;
    
    @ApiModelProperty(value = "方案中介id")
	private Long agencyPlanId;
    
    @ApiModelProperty(value = "中介类型：1招聘负责人自主背调；2中介背调")
    private Integer agencyType;
    
    
    @ApiModelProperty(value = "检查项数组")
	private List<CheckItemEntity> itemList;
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
	public Long getAgencyPlanId() {
		return agencyPlanId;
	}
	public void setAgencyPlanId(Long agencyPlanId) {
		this.agencyPlanId = agencyPlanId;
	}
	public Integer getAgencyType() {
		return agencyType;
	}
	public void setAgencyType(Integer agencyType) {
		this.agencyType = agencyType;
	}
	
	
	
	
	
}
