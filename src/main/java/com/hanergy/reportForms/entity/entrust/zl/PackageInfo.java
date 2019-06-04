package com.hanergy.reportForms.entity.entrust.zl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PackageInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer menuId;
	private String menuName;
	private Integer typeName;
	private BigDecimal totalPrice;
	private BigDecimal discountPrice;
	private String feebackTime;
	private String remark;
	private Integer includeItemNum;
	private List<ItemMenuInfo>  itemMenuInfo;
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getTypeName() {
		return typeName;
	}
	public void setTypeName(Integer typeName) {
		this.typeName = typeName;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getFeebackTime() {
		return feebackTime;
	}
	public void setFeebackTime(String feebackTime) {
		this.feebackTime = feebackTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIncludeItemNum() {
		return includeItemNum;
	}
	public void setIncludeItemNum(Integer includeItemNum) {
		this.includeItemNum = includeItemNum;
	}
	public List<ItemMenuInfo> getItemMenuInfo() {
		return itemMenuInfo;
	}
	public void setItemMenuInfo(List<ItemMenuInfo> itemMenuInfo) {
		this.itemMenuInfo = itemMenuInfo;
	}
	
}	
