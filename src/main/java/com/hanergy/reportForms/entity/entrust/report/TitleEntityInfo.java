package com.hanergy.reportForms.entity.entrust.report;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class TitleEntityInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "抬头名称")
	private String Title;
    
    
    @ApiModelProperty(value = "抬头状态  :   1:出版    ;  2: 终版")
	private Integer status;
    
    
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
