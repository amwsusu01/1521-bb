package com.hanergy.reportForms.mapper.entity;


import com.hanergy.reportForms.entity.log.SystemLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
/*import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;*/

import java.util.Date;

public class ProcessRecode extends SystemLog{
    private String start_date;
    private String end_date;
    private Integer pageNo;
    private Integer pageSize;
    private Boolean isNo;
    private String userFullName;

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = StringUtils.isBlank(start_date)?"":start_date+" 00:00:00";
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = StringUtils.isBlank(end_date)?"":end_date+" 24:00:00";
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getIsNo() {
        return isNo;
    }

    public void setIsNo(Boolean isNo) {
        this.isNo = isNo;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
