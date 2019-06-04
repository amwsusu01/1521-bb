package com.hanergy.reportForms.VO;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName PasswordVo
 * @Description TODO 修改密码
 * @Author DURONGHONG
 * @DATE 2018/10/11 9:37
 * @Version 1.0
 **/
@ApiModel("修改密码")
public class PasswordVo {

    @ApiModelProperty(value = "登陆名")
    private String userName;
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;
    @ApiModelProperty(value = "新密码")
    private String newPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean isEmpty() {
        return !StringUtils.isEmpty(this.userName) && !StringUtils.isEmpty(this.oldPassword) && !StringUtils.isEmpty(this.newPassword);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
