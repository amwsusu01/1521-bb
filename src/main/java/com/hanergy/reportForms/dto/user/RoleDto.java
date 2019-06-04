package com.hanergy.reportForms.dto.user;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName RoleDto
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/10 11:24
 * @Version 1.0
 **/
public class RoleDto {

    @ApiModelProperty(value = "角色id")
    private Long id;
    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "功能列表")
    private List<FunctionDto> functions;

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

    public List<FunctionDto> getFunctions() {
        return functions;
    }

    public void setFunctions(List<FunctionDto> functions) {
        this.functions = functions;
    }
}
