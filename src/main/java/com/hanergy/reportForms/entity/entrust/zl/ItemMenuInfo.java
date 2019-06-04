package com.hanergy.reportForms.entity.entrust.zl;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemMenuInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private BigDecimal discountFee;
	private String surveyProject;
	public BigDecimal getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(BigDecimal discountFee) {
		this.discountFee = discountFee;
	}
	public String getSurveyProject() {
		return surveyProject;
	}
	public void setSurveyProject(String surveyProject) {
		this.surveyProject = surveyProject;
	}
	
}
