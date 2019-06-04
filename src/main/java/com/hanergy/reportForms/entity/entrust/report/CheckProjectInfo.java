package com.hanergy.reportForms.entity.entrust.report;

import com.hanergy.reportForms.mapper.entity.CheckProject;

import io.swagger.annotations.ApiModelProperty;

public class CheckProjectInfo  extends CheckProject{
	private static final long serialVersionUID = 1L;
	public CheckProjectInfo() {
		super();
	}
    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "作废：1是   0否")
    private Integer cancel;

    @ApiModelProperty(value = "删除：1是   0否")
    private Integer deltag;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}

	public Integer getDeltag() {
		return deltag;
	}

	public void setDeltag(Integer deltag) {
		this.deltag = deltag;
	}
    
	
}
