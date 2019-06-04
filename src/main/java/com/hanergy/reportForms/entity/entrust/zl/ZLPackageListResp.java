package com.hanergy.reportForms.entity.entrust.zl;

import java.io.Serializable;
import java.util.List;

public class ZLPackageListResp implements Serializable{
	private static final long serialVersionUID = 1L;
	private String resCode;
	private String errorMsg;
	private List<PackageInfo> data;
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public List<PackageInfo> getData() {
		return data;
	}
	public void setData(List<PackageInfo> data) {
		this.data = data;
	}
	
	
}
