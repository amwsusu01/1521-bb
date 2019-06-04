package com.hanergy.reportForms.entity.entrust.report;

import com.hanergy.reportForms.mapper.entity.*;

import java.io.File;
import java.io.Serializable;
import java.util.List;
/**
 * 专用于第三方报告录入的实体类
 * @author fangshuai
 * @version 1.0.0
 * @time 2018年10月12日
 */
public class UpLoadReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单编号
	 */
	private String orderNumber;
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
	private List<Credentials> credentials;
	/**
	 * 教育背景  （非必填）
	 * 	      
	 */
	private List<EducationBackground> eucationBackground;
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
	private List< com.hanergy.reportForms.mapper.entity.BadRecord> BadRecord;
	/**
	 * 商业利益（非必填）
	 */
	private List<CommercialProfit> commercialProfits;
	/**
	 * 法院诉讼（非必填）
	 */
	private List<CourtProceeds> CourtProceeds;
	/**
	 * 
	 * 金融违规（非必填）
	 */
	private List<FinancialIrregularities> FinancialIrregularities;
	/**
	 * 
	 * 专业资格认证列表
	 */
    private List<ProfessinalCertificate> professinalCertificates;

	/**
	 * 返回的报告路径
	 */
	private File file;

	public Integer getVerify_level() {
		return verify_level;
	}

	public void setVerify_level(Integer verify_level) {
		this.verify_level = verify_level;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Long getBeSelected() {
		return beSelected;
	}
	public void setBeSelected(Long beSelected) {
		this.beSelected = beSelected;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	 
	public Integer getVerify_status() {
		return verify_status;
	}
	public void setVerify_status(Integer verify_status) {
		this.verify_status = verify_status;
	}
	public List<Credentials> getCredentials() {
		return credentials;
	}
	public void setCredentials(List<Credentials> credentials) {
		this.credentials = credentials;
	}
	public List<EducationBackground> getEucationBackground() {
		return eucationBackground;
	}
	public void setEucationBackground(List<EducationBackground> eucationBackground) {
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
	public List<com.hanergy.reportForms.mapper.entity.BadRecord> getBadRecord() {
		return BadRecord;
	}
	public void setBadRecord(List<com.hanergy.reportForms.mapper.entity.BadRecord> badRecord) {
		BadRecord = badRecord;
	}
	public List<CommercialProfit> getCommercialProfits() {
		return commercialProfits;
	}
	public void setCommercialProfits(List<CommercialProfit> commercialProfits) {
		this.commercialProfits = commercialProfits;
	}
	public List<CourtProceeds> getCourtProceeds() {
		return CourtProceeds;
	}
	public void setCourtProceeds(List<CourtProceeds> courtProceeds) {
		CourtProceeds = courtProceeds;
	}
	public List<FinancialIrregularities> getFinancialIrregularities() {
		return FinancialIrregularities;
	}
	public void setFinancialIrregularities(List<FinancialIrregularities> financialIrregularities) {
		FinancialIrregularities = financialIrregularities;
	}
	public List<ProfessinalCertificate> getProfessinalCertificates() {
		return professinalCertificates;
	}
	public void setProfessinalCertificates(List<ProfessinalCertificate> professinalCertificates) {
		this.professinalCertificates = professinalCertificates;
	}
	
}
