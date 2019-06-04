package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 功能
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value="Function对象", description="功能")
public class Function extends Model<Function> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    @ApiModelProperty(value = "上级（功能表的主键），最高级为-1")
    private Long parentId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "菜单URL")
    private String url;
    @ApiModelProperty(value = "控制到方法层面,授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "类型   0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createtime;

    @ApiModelProperty(value = "修改时间")
    private Timestamp updatetime;

    @ApiModelProperty(value = "编码")
    private String code;

    public Long getId() {
        return id;
    }

    public Function() {
    }

    public Function(Long id, String name, String code, Long parentId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.parentId = parentId;
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
    
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
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
