package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author xueyingei
 * @since 2018-10-08
 */
public class UserRoleEntity {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = " 角色（角色表的主键）")
    private List<Long> userroleid;


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

    public List<Long> getUserroleid() {
        return userroleid;
    }

    public void setUserroleid(List<Long> userroleid) {
        this.userroleid = userroleid;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
