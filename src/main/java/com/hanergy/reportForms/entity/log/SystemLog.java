package com.hanergy.reportForms.entity.log;

import org.springframework.data.annotation.Id;


import java.io.Serializable;
import java.util.Date;

//@Document(indexName = "1521_operation_log",type = "processrecode")
public class SystemLog {
    private String id;
    private String systemId;//系统id
    private String systemName;//系统名称
    private String menuId;//菜单id
    private String menuName;//菜单名称
    private String userId;//操作人id
    private String userName;//操作人用户名
    private String fullName;//操作人姓名
    private String loginIpAddr;//操作人ip
    private Date proTime;//操作时间
    private String log_num;//日志编号
    private String log_content;//日志内容
    private String proType;//操作类型 （1：新增，2：修改，3：删除，4:查看，5：登陆，6：导出）
    private String proTarg;//操作对象

    // 利用静态内部类特性实现外部类的单例
    private static class SingleTonBuilder {
        private static SystemLog singleTon = new SystemLog();
    }

    public static SystemLog getInstance() {
        return SingleTonBuilder.singleTon;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLoginIpAddr() {
        return loginIpAddr;
    }

    public void setLoginIpAddr(String loginIpAddr) {
        this.loginIpAddr = loginIpAddr;
    }

    public Date getProTime() {
        return proTime;
    }

    public void setProTime(Date proTime) {
        this.proTime = proTime;
    }

    public String getLog_num() {
        return log_num;
    }

    public void setLog_num(String log_num) {
        this.log_num = log_num;
    }

    public String getLog_content() {
        return log_content;
    }

    public void setLog_content(String log_content) {
        this.log_content = log_content;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProTarg() {
        return proTarg;
    }

    public void setProTarg(String proTarg) {
        this.proTarg = proTarg;
    }

}
