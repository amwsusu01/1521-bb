package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fangshuai
 * @since 2018-09-27
 */
@ApiModel(value="AgencyPlan对象", description="")
public class AgencyPlan extends Model<AgencyPlan> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "方案id")
    private Long template;

    @ApiModelProperty(value = "顺序")
    private Integer seq;


    @ApiModelProperty(value = "中介类型：0招聘负责人自主背调；1中介背调")
    @TableField("agencyType")
    private Integer agencyType;
    
    @ApiModelProperty(value = "中介公司id")
    private Long agency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getTemplate() {
        return template;
    }

    public void setTemplate(Long template) {
        this.template = template;
    }
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    public Integer getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(Integer agencyType) {
        this.agencyType = agencyType;
    }

	public Long getAgency() {
		return agency;
	}

	public void setAgency(Long agency) {
		this.agency = agency;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
