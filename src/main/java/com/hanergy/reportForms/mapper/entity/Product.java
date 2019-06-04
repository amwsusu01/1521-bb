package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private Long proid;
    private Long userId;
    private String proName;
    private String targetDate;
    private Long monDataId;
    private String monData;
    private Integer monDataType;
    private String month;
    private Long wekDataId;
    private String wekData;
    private String week;
    private Integer wekDataType;
    private Integer processType;//操作类型  1：新建 2：编辑
    private List<JSONObject> monList=new ArrayList<>();

    public Integer getProcessType() {
        return processType;
    }

    public void setProcessType(Integer processType) {
        this.processType = processType;
    }

    public Long getProid() {
        return proid;
    }

    public void setProid(Long proid) {
        this.proid = proid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public Long getMonDataId() {
        return monDataId;
    }

    public void setMonDataId(Long monDataId) {
        this.monDataId = monDataId;
    }

    public String getMonData() {
        return monData;
    }

    public void setMonData(String monData) {
        this.monData = monData;
    }

    public Integer getMonDataType() {
        return monDataType;
    }

    public void setMonDataType(Integer monDataType) {
        this.monDataType = monDataType;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getWekDataId() {
        return wekDataId;
    }

    public void setWekDataId(Long wekDataId) {
        this.wekDataId = wekDataId;
    }

    public String getWekData() {
        return wekData;
    }

    public void setWekData(String wekData) {
        this.wekData = wekData;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getWekDataType() {
        return wekDataType;
    }

    public void setWekDataType(Integer wekDataType) {
        this.wekDataType = wekDataType;
    }

    public List<JSONObject> getMonList() {
        return monList;
    }

    public void setMonList(List<JSONObject> monList) {
        this.monList = monList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "proid=" + proid +
                ", userId=" + userId +
                ", proName='" + proName + '\'' +
                ", targetDate='" + targetDate + '\'' +
                ", monDataId=" + monDataId +
                ", monData='" + monData + '\'' +
                ", monDataType=" + monDataType +
                ", month='" + month + '\'' +
                ", wekDataId=" + wekDataId +
                ", wekData='" + wekData + '\'' +
                ", week='" + week + '\'' +
                ", wekDataType=" + wekDataType +
                '}';
    }
}
