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
 * 方案模板
 * </p>
 *
 * @author fangshuai
 * @since 2018-09-27
 */
//@TableName("checkTemplate")
@ApiModel(value="CheckTemplate对象", description="方案模板")
public class CheckTemplate extends Model<CheckTemplate> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "背调方案（表的主键）")
    @TableField("agencyPlanId")
    private Long agencyPlanId;

    @ApiModelProperty(value = "背调顺序")
    private Integer seq;

    @ApiModelProperty(value = "背调项目（表的主键）")
    private Long project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getAgencyPlanId() {
        return agencyPlanId;
    }

    public void setAgencyPlanId(Long agencyPlanId) {
        this.agencyPlanId = agencyPlanId;
    }
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
