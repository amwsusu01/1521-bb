package com.hanergy.reportForms.entity.entrust.report;

import java.io.Serializable;

import java.util.List;


import io.swagger.annotations.ApiModelProperty;

public class EntrustReportParamsResp  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "参数信息")
   private List<EntrustParamsInfo> paramInfo;
	
	
	

public EntrustReportParamsResp(List<EntrustParamsInfo> paramInfo ) {
		super();
		this.paramInfo = paramInfo;
	}

public List<EntrustParamsInfo> getParamInfo() {
	return paramInfo;
}

public void setParamInfo(List<EntrustParamsInfo> paramInfo) {
	this.paramInfo = paramInfo;
}

	
}
