package com.hanergy.reportForms.entity.template;

import java.io.Serializable;
import java.util.List;

import com.hanergy.reportForms.entity.entrust.report.TemplatePlanInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="模版实体类", description="模版实体类")
public class TemplateEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "模版id")
	private Long id;
	
	@ApiModelProperty(value = "模版编码")
	private String code ;
	
	@ApiModelProperty(value = "模版名称")
	private String name ;
	
	@ApiModelProperty(value = "模版名称")
	private String cancel;
	
	@ApiModelProperty(value = "检查项目 （数组）")
    private List<TemplatePlanInfo> relationInfo;
 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getCancel() {
		return cancel;
	}
	public void setCancel(String cancel) {
		this.cancel = cancel;
	}
	public List<TemplatePlanInfo> getRelationInfo() {
		return relationInfo;
	}
	public void setRelationInfo(List<TemplatePlanInfo> relationInfo) {
		this.relationInfo = relationInfo;
	} 
	
	
}
