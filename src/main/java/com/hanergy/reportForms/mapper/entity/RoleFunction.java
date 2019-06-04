package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 角色功能
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value="RoleFunction对象", description="角色功能")
public class RoleFunction extends Model<RoleFunction> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色（角色表的主键）")
    private Long role;

    @ApiModelProperty(value = "功能（功能表的主键）")
    private Long function;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }
    public Long getFunction() {
        return function;
    }

    public void setFunction(Long function) {
        this.function = function;
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
