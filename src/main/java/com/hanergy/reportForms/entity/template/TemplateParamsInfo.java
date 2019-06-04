package com.hanergy.reportForms.entity.template;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hanergy.reportForms.mapper.entity.Agency;
import com.hanergy.reportForms.mapper.entity.CheckItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="创建模版请求实体类", description="前台需要传输的数据模型")
public class TemplateParamsInfo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
    public TemplateParamsInfo(Map<Integer, String> postType, Map<Integer, String> workType, List<Long> rank,
			Map<Integer, String> cancel, List<Agency> agency) {
		super();
		this.postType = postType;
		this.workType = workType;
		this.rank = rank;
		this.cancel = cancel;
		this.agency = agency;
	}

	@ApiModelProperty(value = "岗位类型参数")
    @TableField("postType")
    private Map<Integer ,String >  postType;
    
    @ApiModelProperty(value = "员工类型参数")
    @TableField("workType")
    private Map<Integer ,String >  workType;

    @ApiModelProperty(value = "职级")
    private List<Long> rank;
    
    @ApiModelProperty(value = "模板状态参数")
    private Map<Integer ,String >  cancel;
    
    @ApiModelProperty(value = "调查方参数")
    private  List<Agency> agency;
    
    @ApiModelProperty(value = "检查项数组")
    private List<CheckItem> itemList;
    
	public List<CheckItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CheckItem> itemList) {
		this.itemList = itemList;
	}

	public Map<Integer, String> getPostType() {
		return postType;
	}

	public void setPostType(Map<Integer, String> postType) {
		this.postType = postType;
	}

	public Map<Integer, String> getWorkType() {
		return workType;
	}

	public void setWorkType(Map<Integer, String> workType) {
		this.workType = workType;
	}

	public List<Long> getRank() {
		return rank;
	}

	public void setRank(List<Long> rank) {
		this.rank = rank;
	}

	public Map<Integer, String> getCancel() {
		return cancel;
	}

	public void setCancel(Map<Integer, String> cancel) {
		this.cancel = cancel;
	}

	public List<Agency> getAgency() {
		return agency;
	}

	public void setAgency(List<Agency> agency) {
		this.agency = agency;
	}

 

 
 
	 
 
}
