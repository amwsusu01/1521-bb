package com.hanergy.reportForms.commons;

import javax.validation.constraints.NotNull;

public class ElsCommonParam {
    @NotNull
    private String  indexName;//索引名
    private String indexType;//索引类型
    private String id;//索引ID
    private String propName;//对象属性
    private String propValue;//属性值
    private Object data;//数据
    private String ipAddr;//ip
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @Override
    public String toString() {
        return "ElsCommonParam{" +
                "indexName='" + indexName + '\'' +
                ", indexType='" + indexType + '\'' +
                ", id='" + id + '\'' +
                ", propName='" + propName + '\'' +
                ", propValue='" + propValue + '\'' +
                ", data=" + data +
                ", ipAddr='" + ipAddr + '\'' +
                '}';
    }
}
