package com.hanergy.reportForms.entity.entrust.report;

import java.io.Serializable;


import io.swagger.annotations.ApiModelProperty;

public class CheckParams implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "报告编码：当核实来源为“中介”，此项要填写")
    private String reportNumber;

    @ApiModelProperty(value = "核实来源：1候选人   2人力    3中介")
    private Integer verifySource = 0;

    @ApiModelProperty(value = "核实单位：来源为人力则为用户表主键，来源为中介则为中介表主键")
    private long verifyUnit;

    @ApiModelProperty(value = "核实单位名称")
    private String verifyName;

    @ApiModelProperty(value = "核实人员")
    private String verifyPerson;

    @ApiModelProperty(value = "核实说明")
    private String verifyExplain;

    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯")
    private int verifyLevel;

    @ApiModelProperty(value = "核实状态：1终版    2初版")
    private int verifyStatus;

    @ApiModelProperty(value = "审核人员")
    private long examine;

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public Integer getVerifySource() {
		return verifySource;
	}

	public void setVerifySource(Integer verifySource) {
		this.verifySource = verifySource;
	}

	public long getVerifyUnit() {
		return verifyUnit;
	}

	public void setVerifyUnit(long verifyUnit) {
		this.verifyUnit = verifyUnit;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	public String getVerifyPerson() {
		return verifyPerson;
	}

	public void setVerifyPerson(String verifyPerson) {
		this.verifyPerson = verifyPerson;
	}

	public String getVerifyExplain() {
		return verifyExplain;
	}

	public void setVerifyExplain(String verifyExplain) {
		this.verifyExplain = verifyExplain;
	}

	public int getVerifyLevel() {
		return verifyLevel;
	}

	public void setVerifyLevel(int verifyLevel) {
		this.verifyLevel = verifyLevel;
	}

	public int getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(int verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public long getExamine() {
		return examine;
	}

	public void setExamine(long examine) {
		this.examine = examine;
	}

	 
	 
}
