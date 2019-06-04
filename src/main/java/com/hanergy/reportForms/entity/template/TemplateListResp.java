package com.hanergy.reportForms.entity.template;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="模版列表实体类", description="模版列表实体类")
public class TemplateListResp implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "模版列表")
    private List<TemplateInfo> templateList;
	private Integer currentPage;
	private Integer size;
	private Integer totalSize;
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	public List<TemplateInfo> getTemplateList() {
		return templateList;
	}
	public void setTemplateList(List<TemplateInfo> templateList) {
		this.templateList = templateList;
	}
 

	 
	  
	
}
