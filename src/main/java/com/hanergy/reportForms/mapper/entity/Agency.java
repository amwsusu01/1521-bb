package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.hanergy.reportForms.entity.template.CheckItemEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 中介 就是需求中的“供应商”
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value = "Agency对象", description = "中介 就是需求中的“供应商”")
//@JsonIgnoreProperties({"password","hanergyAccount","hanergyPassword"})
public class Agency extends Model<Agency> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "中介公司登陆背调系统的账号", hidden = true)
    private String loginName;
    @ApiModelProperty(value = "中介公司登陆背调系统的密码", hidden = true)

    private String password;
    @ApiModelProperty(value = "邮箱")
    private String mail;
    @ApiModelProperty(value = "背调系统登陆中介公司的账号", hidden = true)
    private String hanergyAccount;
    @ApiModelProperty(value = "背调系统登陆中介公司的密码", hidden = true)
    private String hanergyPassword;
    @ApiModelProperty(value = "作废：1是   0否")
    private Integer cancel;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private List<CheckItemEntity> checkItems;

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public List<CheckItemEntity> getCheckItems() {
        return checkItems;
    }

    public void setCheckItems(List<CheckItemEntity> checkItems) {
        this.checkItems = checkItems;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getHanergyAccount() {
        return hanergyAccount;
    }

    public void setHanergyAccount(String hanergyAccount) {
        this.hanergyAccount = hanergyAccount;
    }

    public String getHanergyPassword() {
        return hanergyPassword;
    }

    public void setHanergyPassword(String hanergyPassword) {
        this.hanergyPassword = hanergyPassword;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
