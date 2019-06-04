package com.hanergy.reportForms.commons.utils.sfsys;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @ClassName Personal
 * @Description TODO 人事
 * @Author DURONGHONG
 * @DATE 2018/10/26 16:37
 * @Version 1.0
 **/
public class Personal implements Serializable {

    private static final long serialVersionUID = -3739588527922517454L;

    // 姓名--> 名
    private String firstName;
    // 姓名 --> 姓
    private String lastName;
    // 工号,有可能出现工号不能及时更新的问题
    private String middleName;
    // 系统中的唯一标识
    private String userId;
    // 邮箱前缀
    private String userName;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
