package com.hanergy.reportForms.entity.entrust.report;

import java.util.Date;

import com.hanergy.reportForms.mapper.entity.CheckItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="CheckItemInfo对象", description="背调项目")
public class CheckItemInfo extends CheckItem{
	
	public CheckItemInfo() {
		super();
	}
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "背调项id")
    private Long checkPlanId;
	
	@ApiModelProperty(value = "审核内容")
    private String verifyContent;
	
 	@ApiModelProperty(value = "核实来源：1候选人   2人力    3中介")
    private Integer verifySource;

    @ApiModelProperty(value = "核实说明")
    private String verifyExplain;

    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯")
    private Integer verifyLevel;

    @ApiModelProperty(value = "调查日期")
    private Date investigationTime;

    @ApiModelProperty(value = "证明机构")
    private String certificationBody;

	 

	public Long getCheckPlanId() {
		return checkPlanId;
	}

	public void setCheckPlanId(Long checkPlanId) {
		this.checkPlanId = checkPlanId;
	}

	public String getVerifyContent() {
		return verifyContent;
	}

	public void setVerifyContent(String verifyContent) {
		this.verifyContent = verifyContent;
	}

	public Integer getVerifySource() {
		return verifySource;
	}

	public void setVerifySource(Integer verifySource) {
		this.verifySource = verifySource;
	}

	public String getVerifyExplain() {
		return verifyExplain;
	}

	public void setVerifyExplain(String verifyExplain) {
		this.verifyExplain = verifyExplain;
	}

	public Integer getVerifyLevel() {
		return verifyLevel;
	}

	public void setVerifyLevel(Integer verifyLevel) {
		this.verifyLevel = verifyLevel;
	}

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
