package com.hanergy.reportForms.VO;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName AgencyVO
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/26 10:37
 * @Version 1.0
 **/
public class AgencyVO implements Serializable {

    @ApiModelProperty(value = "中介表主键")
    private Long id;
    @ApiModelProperty(value = "中介名称")
    private String name;
    @ApiModelProperty(value = "中介方案Id")
    private Long agencyPlanId;
    @ApiModelProperty(value = "核实来源: 2--人力/SF  3--中介")
    private Integer verifySource;

    public Integer getVerifySource() {
        return verifySource;
    }

    public void setVerifySource(Integer verifySource) {
        this.verifySource = verifySource;
    }

    public Long getAgencyPlanId() {
        return agencyPlanId;
    }

    public void setAgencyPlanId(Long agencyPlanId) {
        this.agencyPlanId = agencyPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
