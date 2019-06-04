package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * <p>
 * 角色功能
 * </p>
 *
 * @author xueyingei
 * @since 2018-10-08
 */
public class RoleFunctionEntity {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "作废：1是   0否")
    private Integer cancel;

    @ApiModelProperty(value = "角色功能表functionid")
    private List<Long> rolefunctionid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

    public List<Long> getRolefunctionid() {
        return rolefunctionid;
    }

    public void setRolefunctionid(List<Long> rolefunctionid) {
        this.rolefunctionid = rolefunctionid;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
