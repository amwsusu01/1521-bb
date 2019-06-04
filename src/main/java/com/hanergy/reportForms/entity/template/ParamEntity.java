package com.hanergy.reportForms.entity.template;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class ParamEntity implements Serializable{

	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "属性名称")
	private String name;
    
    @ApiModelProperty(value = "属性代码")
	private Integer code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
}
