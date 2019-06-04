package com.hanergy.reportForms.entity.entrust.report;

import com.hanergy.reportForms.entity.entrust.report.wrapper.*;
import com.hanergy.reportForms.mapper.entity.Experience;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class EntrustParamsInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "核实单位名称")
	private String verify_name;
	
	@ApiModelProperty(value = "核实来源")
	private String verify_source;
	@ApiModelProperty(value = "核实状态：1终版    2初版")
	private Integer verify_status;
	@ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯")
	private Integer verify_level;
	
	@ApiModelProperty(value = "核实单位：来源为人力则为用户表主键，来源为中介则为中介表主键")
	private Long verifyUnit;
	@ApiModelProperty(value = "背调id")
	private Long checkId;
	@ApiModelProperty(value = "候选人id")
	private Long beSelected;
	@ApiModelProperty(value = "检查项列表")
	private List<CheckItemInfo> itemList;

	public Integer getVerify_level() {
		return verify_level;
	}

	public void setVerify_level(Integer verify_level) {
		this.verify_level = verify_level;
	}

	/**
	  * 身份信息
	  */
	private List<CredentialsWrapper> credentials;
	/**
	 * 教育背景（）
	 */
	private List<EducationBackgroundWrapper> eucationBackground;
	/**
	 * 第一段经历
	 */
	private Experience experiencesOne;
	/**
	 * 第二段经历
	 */
	private Experience experiencesTwo;
	/**
	 * 第三段经历
	 */
	private Experience experiencesThree;
	/**
	 * 不良记录
	 */
	private List<BadRecordWrapper> BadRecord;
	/**
	 * 商业利益
	 */
	private List<CommercialProfitWrapper> commercialProfits;
	/**
	 * 法院诉讼
	 */
	private List<CourtProceedsWrapper> CourtProceeds;
	/**
	 * 
	 * 金融违规
	 */
	private List<FinancialIrregularitiesWrapper> FinancialIrregularities;
	
	
	 
 
	public String getVerify_name() {
		return verify_name;
	}
	public void setVerify_name(String verify_name) {
		this.verify_name = verify_name;
	}
	public Integer getVerify_status() {
		return verify_status;
	}
	public void setVerify_status(Integer verify_status) {
		this.verify_status = verify_status;
	}
  
 
	public List<CheckItemInfo> getItemList() {
		return itemList;
	}
	public void setItemList(List<CheckItemInfo> itemList) {
		this.itemList = itemList;
	}
	public Long getCheckId() {
		return checkId;
	}
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	public String getVerify_source() {
		return verify_source;
	}
	public void setVerify_source(String verify_source) {
		this.verify_source = verify_source;
	}
	
	public Long getVerifyUnit() {
		return verifyUnit;
	}
	public void setVerifyUnit(Long verifyUnit) {
		this.verifyUnit = verifyUnit;
	}
	public Long getBeSelected() {
		return beSelected;
	}
	public void setBeSelected(Long beSelected) {
		this.beSelected = beSelected;
	}
	 
	public Experience getExperiencesOne() {
		return experiencesOne;
	}
	public void setExperiencesOne(Experience experiencesOne) {
		this.experiencesOne = experiencesOne;
	}
	public Experience getExperiencesTwo() {
		return experiencesTwo;
	}
	public void setExperiencesTwo(Experience experiencesTwo) {
		this.experiencesTwo = experiencesTwo;
	}
	public Experience getExperiencesThree() {
		return experiencesThree;
	}
	public void setExperiencesThree(Experience experiencesThree) {
		this.experiencesThree = experiencesThree;
	}
	public List<CredentialsWrapper> getCredentials() {
		return credentials;
	}
	public void setCredentials(List<CredentialsWrapper> credentials) {
		this.credentials = credentials;
	}
	public List<EducationBackgroundWrapper> getEucationBackground() {
		return eucationBackground;
	}
	public void setEucationBackground(List<EducationBackgroundWrapper> eucationBackground) {
		this.eucationBackground = eucationBackground;
	}
	public List<BadRecordWrapper> getBadRecord() {
		return BadRecord;
	}
	public void setBadRecord(List<BadRecordWrapper> badRecord) {
		BadRecord = badRecord;
	}
	public List<CommercialProfitWrapper> getCommercialProfits() {
		return commercialProfits;
	}
	public void setCommercialProfits(List<CommercialProfitWrapper> commercialProfits) {
		this.commercialProfits = commercialProfits;
	}
	public List<CourtProceedsWrapper> getCourtProceeds() {
		return CourtProceeds;
	}
	public void setCourtProceeds(List<CourtProceedsWrapper> courtProceeds) {
		CourtProceeds = courtProceeds;
	}
	public List<FinancialIrregularitiesWrapper> getFinancialIrregularities() {
		return FinancialIrregularities;
	}
	public void setFinancialIrregularities(List<FinancialIrregularitiesWrapper> financialIrregularities) {
		FinancialIrregularities = financialIrregularities;
	}
	 
	 
	
}
