package com.hanergy.reportForms.entity.entrust.report.wrapper;

import java.util.Date;

import com.hanergy.reportForms.mapper.entity.CommercialProfit;

import io.swagger.annotations.ApiModelProperty;

public class CommercialProfitWrapper extends CommercialProfit{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "调查日期" )
	private Date investigationTime;
    
    @ApiModelProperty(value = "证明机构")
	private String certificationBody;

	public Date getInvestigationTime() {
		return investigationTime;
	}

	public void setInvestigationTime(Date investigationTime) {
		this.investigationTime = investigationTime;
	}

	public String getCertificationBody() {
		return certificationBody;
	}

	public void setCertificationBody(String certificationBody) {
		this.certificationBody = certificationBody;
	}

}
