package com.hanergy.reportForms.entity.entrust.report;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UpLoadUserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Long userId ;
	/**
	 * 用户姓名
	 */
	@ApiModelProperty(value = "用户名称")
	private String name ;
	/**
	 * 用户类型：1:背调负责人  ; 2: 招聘负责人   ; 3:第三方中介
	 */
	@ApiModelProperty(value = "用户类型 ： 1:背调负责人  ; 2: 招聘负责人   ; 3:第三方中介")
	private Integer userType;
	 
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}
