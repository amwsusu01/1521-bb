package com.hanergy.reportForms.entity.template;

import java.io.Serializable;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class TemplateInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "模版id")
	private Long id;
	
	@ApiModelProperty(value = "模版编码")
	private String code ;
	
	@ApiModelProperty(value = "模版名称")
	private String name ;
	
	@ApiModelProperty(value = "模版名称")
	private String cancel;
	
	@ApiModelProperty(value = "当前模板是否可编辑 : 0: 可编辑 ; 1：不可编辑")
	private Integer isEdit;
	
	@ApiModelProperty(value = "岗位类型：1资金相关   0非资金相关   2销售岗   3中睿岗位   4财富(拓丰)岗位")
	private Integer postType;
	
	@ApiModelProperty(value = "员工类型（必填）：1正式（正式员工必填职级）   2应届   3实习")
	private Integer workType;
	
	@ApiModelProperty(value = "职级")
	private Integer rank;
	
	@ApiModelProperty(value = "职级最大值")
	private Integer rankMax;
	@ApiModelProperty(value = "检查项目 （数组）")
    private List<TemplatePlanEntitiy> relationArr;
 
	public Integer getPostType() {
		return postType;
	}
	public void setPostType(Integer postType) {
		this.postType = postType;
	}
	public Integer getWorkType() {
		return workType;
	}
	public void setWorkType(Integer workType) {
		this.workType = workType;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public Integer getRankMax() {
		return rankMax;
	}
	public void setRankMax(Integer rankMax) {
		this.rankMax = rankMax;
	}
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
	public List<TemplatePlanEntitiy> getRelationArr() {
		return relationArr;
	}
	public void setRelationArr(List<TemplatePlanEntitiy> relationArr) {
		this.relationArr = relationArr;
	}
	public Integer getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(Integer isEdit) {
		this.isEdit = isEdit;
	}
	 
	 
	
	


}
