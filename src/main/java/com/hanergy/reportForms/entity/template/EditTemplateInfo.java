package com.hanergy.reportForms.entity.template;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hanergy.reportForms.commons.utils.MatchUtil;
import com.hanergy.reportForms.commons.utils.StrUtil;
import com.hanergy.reportForms.entity.entrust.report.TemplatePlanInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="编辑模板实体类", description="编辑模板实体类")
public class EditTemplateInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "模板id (从获取模板列表接口中获取)")
	private Long id;
	@ApiModelProperty(value = "模板名称 ")
    private String name;

	@ApiModelProperty(value = "模板编号 ")
    private String code;

	
    @ApiModelProperty(value = "岗位类型：1资金相关   0非资金相关   2销售岗   3中睿岗位   4财富(拓丰)岗位（前台必传字段）")
    @TableField("postType")
    private Integer postType;

    @ApiModelProperty(value = "员工类型（必填）：1正式（正式员工必填职级）   2应届   3实习  （前台必传字段）")
    @TableField("workType")
    private Integer workType;

    @ApiModelProperty(value = "职级（1-30级）（前台必传字段）")
    private Integer rank;
    
    @ApiModelProperty(value = "职级最大值（1-30级）（前台必传字段）")
    private Integer rankMax;
    
    @ApiModelProperty(value = "调查方：0招聘负责人   1背调公司  （前台必传字段）")
    private Integer investUnit;
    
    @ApiModelProperty(value = "模板状态  : 0 :正常   ; 1：停止")
    private Integer cancel;
    
    @ApiModelProperty(value = "模板描述")
    private String des;
    
    @ApiModelProperty(value = "关联项目信息")
    private List<TemplatePlanInfo> relationArr;
    
	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Integer getInvestUnit() {
		return investUnit;
	}

	public void setInvestUnit(Integer investUnit) {
		this.investUnit = investUnit;
	}

	public Integer getCancel() {
		return cancel;
	}

	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<TemplatePlanInfo> getRelationArr() {
		return relationArr;
	}

	public void setRelationArr(List<TemplatePlanInfo> relationArr) {
		this.relationArr = relationArr;
	}

	public boolean checkParam(){
    	if(StrUtil.isEmpty(this.id)||
    			StrUtil.isEmpty(this.name)||
    			StrUtil.isEmpty(this.name)||
    			StrUtil.isEmpty(this.postType)||
    			StrUtil.isEmpty(this.workType)||
    			StrUtil.isEmpty(this.rank)||
    			StrUtil.isEmpty(this.des)||
    			StrUtil.isEmpty(this.investUnit)||
    			StrUtil.isEmpty(this.cancel)){
    		return false;
    	}
    	return true;
    } 
    public boolean paramsVerfy(){
		   if(!MatchUtil.TEMPLATE_POST_TYPE_MAP.containsKey(this.postType)||
				   !MatchUtil.TEMPLATE_WORK_TYPE_MAP.containsKey(this.workType)||
				   !MatchUtil.TEMPLATE_INVEST_UNIT_MAP.containsKey(this.investUnit)||
				   !MatchUtil.RANK_LIST.contains(this.rank)||
				   !MatchUtil.TEMPLATE_CANCEL_MAP.containsKey(this.cancel) ){
			   return false;
		   }
		   return true;
		   
	   }
	
}
