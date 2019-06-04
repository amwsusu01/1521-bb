package com.hanergy.reportForms.dto.user;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName FunctionDto
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/10 11:25
 * @Version 1.0
 **/
@ApiModel(value = "功能")
public class FunctionDto implements Serializable {

    private static final long serialVersionUID = 5899510994098373698L;
    @ApiModelProperty(value = "功能id")
    private Long id;
    @ApiModelProperty(value = "功能名称")
    private String name;
    @ApiModelProperty("功能编码")
    private String code;
    @ApiModelProperty(value = "父级Id")
    private Long parentId;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
