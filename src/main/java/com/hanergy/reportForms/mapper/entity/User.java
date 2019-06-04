package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value="User对象", description="用户")
//@JsonIgnoreProperties({"password","salt"})
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "登陆名")
    private String name;

    @ApiModelProperty(value = "密码",hidden = true)
    private String password;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "工号")
    private String jobNumber;

    @ApiModelProperty(value = "作废：1是   0否")
    private Integer cancel;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createtime;

    @ApiModelProperty(value = "修改时间")
    private Timestamp updatetime;

    @ApiModelProperty(value = "用户角色")
    @TableField(exist = false)
    private List<Role> roles;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
