package com.hanergy.reportForms.dto.beselected;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName PlanDto
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/8 17:44
 * @Version 1.0
 **/
@ApiModel(value = "方案中介")
public class TemplatePlanDto implements Serializable {

    @ApiModelProperty(value = "方案中介id")
    private Long id;
    @ApiModelProperty(value = "中介类型：1招聘负责人自主背调；2中介背调")
    private Integer agencyType;
    @ApiModelProperty(value = "顺序")
    private Integer seq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(Integer agencyType) {
        this.agencyType = agencyType;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
