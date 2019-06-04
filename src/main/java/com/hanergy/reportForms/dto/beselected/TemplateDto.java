package com.hanergy.reportForms.dto.beselected;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName TemplateDto
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/8 17:35
 * @Version 1.0
 **/
@ApiModel(value = "模板信息")
public class TemplateDto implements Serializable {

    @ApiModelProperty(value = "模板id")
    private Long id;
    @ApiModelProperty(value = "模板名称")
    private String name;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "方案中介")
    private List<TemplatePlanDto> plans;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TemplatePlanDto> getPlans() {
        return plans;
    }

    public void setPlans(List<TemplatePlanDto> plans) {
        this.plans = plans;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
