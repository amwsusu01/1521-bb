package com.hanergy.reportForms.dto.user;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.mapper.entity.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserDto
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/10/10 11:17
 * @Version 1.0
 **/
@ApiModel(value = "本类主要封装用户登陆成功之后返回的信息")
public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户id或者公司id,或者候选人id")
    private Long id;
    @ApiModelProperty(value = "用户名字或公司名称或候选人名称")
    private String name;
    @ApiModelProperty(value = "登陆用户名")
    private String userName;
    @ApiModelProperty(value = "邮箱")
    private String mail;
    @ApiModelProperty(value = "工号,仅使用与公司内人员")
    private String jobNumber;
    @ApiModelProperty(value = "手机号码")
    private String mobilePhone;
    @ApiModelProperty(value = "角色列表")
    private List<Role> roles;
    @ApiModelProperty(value = "用户类型,1-公司用户,2-中介,3-候选人用户")
    private Integer userType;
    @ApiModelProperty(value = "用户状态: 1-禁用,0-正常")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
