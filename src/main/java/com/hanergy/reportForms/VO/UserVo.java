package com.hanergy.reportForms.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName UserVo
 * @Description TODO 用户登陆使用
 * @Author DURONGHONG
 * @DATE 2018/10/11 9:36
 * @Version 1.0
 **/
@ApiModel(value = "用户登陆使用")
public class UserVo implements Serializable {

    private static final long serialVersionUID = -978789614039507316L;

    @ApiModelProperty(value = "登陆名")
    private String name;
    @ApiModelProperty(value = "密码")
    private String password;

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
}
