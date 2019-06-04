package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 背调方案
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value="Template对象", description="背调方案")
public class Template extends Model<Template> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键 （非前台传输字段）")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "编号（非前台传输字段）")
    private String code;

    @ApiModelProperty(value = "名称：用户不填写则用“岗位类型”、“员工类型”、“职级”组合（非前台传输字段）")
    private String name;

    @ApiModelProperty(value = "岗位类型：1资金相关   0非资金相关   2销售岗   3中睿岗位   4财富(拓丰)岗位（前台必传字段）")
    @TableField("postType")
    private Integer postType;

    @ApiModelProperty(value = "员工类型（必填）：1正式（正式员工必填职级）   2应届   3实习  （前台必传字段）")
    @TableField("workType")
    private Integer workType;

    @ApiModelProperty(value = "职级（1-30级）（前台必传字段）")
    private Integer rank;
    
    @ApiModelProperty(value = "职级最大值（1-30级）（前台必传字段）")
    @TableField("rank_max")
    private Integer rankMax;
    
    @ApiModelProperty(value = "作废：1是   0否 （非前台传输字段） ")
    private Integer cancel;

    @ApiModelProperty(value = "删除：1是   0否 （非前台传输字段）")
    private Integer deltag;
    
    @ApiModelProperty(value = "模板描述")
    private String des;
    
    @ApiModelProperty(value = "调查方：0招聘负责人   1背调公司  （前台必传字段）")
    @TableField("investUnit")
    private Integer investUnit;
 

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

	public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

    public Integer getDeltag() {
        return deltag;
    }

    public void setDeltag(Integer deltag) {
        this.deltag = deltag;
    }

    public Integer getInvestUnit() {
        return investUnit;
    }

    public void setInvestUnit(Integer investUnit) {
        this.investUnit = investUnit;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
