package com.hanergy.reportForms.entity.entrust.report;

import com.hanergy.reportForms.entity.entrust.report.wrapper.*;
import com.hanergy.reportForms.mapper.entity.Experience;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class UpLoadReportInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "上传用户信息")
	/**
	 * 用户信息 （必填项）
	 */
	private UpLoadUserInfo  userInfo;
	/**
	 * 检查项信息(必填)
	 */
	private List<CheckItemInfo>  itemList;
	
	/**
	 * 背调id(必填)
	 */
	private Long checkId;
	/**
	 * 候选人id(必填)
	 */
	private Long beSelected;
	
	/**
	 * 提交状态   1：终版 ;2:初版 （必填）
	 */
	private Integer verify_status;

	/**
	 * 评定等级
	 */
	private Integer verify_level;
	
	 /**
	  * 身份信息 （非必填）
	  * 	   	   
	  */
	private List<CredentialsWrapper> credentials;
	/**
	 * 教育背景  （非必填）
	 * 	      
	 */
	private List<EducationBackgroundWrapper> eucationBackground;
	/**
	 * 第一段经历（非必填）
	 */
	private Experience experiencesOne;
	/**
	 * 第二段经历（非必填）
	 */
	private Experience experiencesTwo;
	/**
	 * 第三段经历（非必填）
	 */
	private Experience experiencesThree;
	/**
	 * 不良记录（非必填）
	 */
	private List<BadRecordWrapper> BadRecord;
	/**
	 * 商业利益（非必填）
	 */
	private List<CommercialProfitWrapper> commercialProfits;
	/**
	 * 法院诉讼（非必填）
	 */
	private List<CourtProceedsWrapper> CourtProceeds;
	/**
	 * 
	 * 金融违规（非必填）
	 */
	private List<FinancialIrregularitiesWrapper> FinancialIrregularities;

	public Integer getVerify_level() {
		return verify_level;
	}

	public void setVerify_level(Integer verify_level) {
		this.verify_level = verify_level;
	}

	public UpLoadUserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UpLoadUserInfo userInfo) {
		this.userInfo = userInfo;
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
	public Long getBeSelected() {
		return beSelected;
	}
	public void setBeSelected(Long beSelected) {
		this.beSelected = beSelected;
	}
	public Integer getVerify_status() {
		return verify_status;
	}
	public void setVerify_status(Integer verify_status) {
		this.verify_status = verify_status;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	
}
