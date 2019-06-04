package com.hanergy.reportForms.commons;


import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.entity.log.SystemLog;
import com.hanergy.reportForms.mapper.entity.JobApplication;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CommonSearchParam extends SystemLog {
    private String jituan;//控股集团
    private List<String> jituanList;
    private String shiyequn;//事业群
    private List<String> shiyequnList;
    private String shiyebu;//事业部
    private List<String> shiyebuList;
    private String shenggongsi;//省公司/分公司
    private List<String> shenggongsiList;
    private String dept;
    private String jobGrade;
    private String employeeName;
    private String employeeID;
    private String beginDate;
    private String endDate;
    private Integer page;
    private Integer pageSize;
    private boolean isNo;
    private Integer type;
    private String tabType;
    private List<String> deptList;
    private List<String> jobGradeList;

    public String getTabType() {
        return tabType;
    }

    public String getDept() {
        return dept;
    }

    public boolean getIsNo() {
        return isNo;
    }

    public void setIsNo(boolean no) {
        isNo = no;
    }

    public List<String> getJituanList() {
        return jituanList;
    }

    public List<String> getShiyequnList() {
        return shiyequnList;
    }

    public List<String> getShiyebuList() {
        return shiyebuList;
    }

    public List<String> getShenggongsiList() {
        return shenggongsiList;
    }

    public String getJituan() {
        return jituan;
    }

    public void setJituan(String jituan) {
        this.jituan = jituan;
        if (StringUtils.isNotBlank(jituan) && jituan.indexOf(",") > 0) {
            this.jituanList = Arrays.asList(jituan.trim().split(","));
            this.jituan = "";
        }
    }

    public String getShiyequn() {
        return shiyequn;
    }

    public void setShiyequn(String shiyequn) {
        this.shiyequn = shiyequn;
        if (StringUtils.isNotBlank(shiyequn) && shiyequn.indexOf(",") > 0) {
            this.shiyequnList = Arrays.asList(shiyequn.trim().split(","));
            this.shiyequn = "";
        }
    }

    public String getShiyebu() {
        return shiyebu;
    }

    public void setShiyebu(String shiyebu) {
        this.shiyebu = shiyebu;
        if (StringUtils.isNotBlank(shiyebu) && shiyebu.indexOf(",") > 0) {
            this.shiyebuList = Arrays.asList(shiyebu.trim().split(","));
            this.shiyebu = "";
        }
    }

    public String getShenggongsi() {
        return shenggongsi;
    }

    public void setShenggongsi(String shenggongsi) {
        this.shenggongsi = shenggongsi;
        if (StringUtils.isNotBlank(shenggongsi) && shenggongsi.indexOf(",") > 0) {
            this.shenggongsiList = Arrays.asList(shenggongsi.trim().split(","));
            this.shenggongsi = "";
        }
    }

    public List<String> getDeptList() {
        return deptList;
    }

    public List<String> getJobGradeList() {
        return jobGradeList;
    }

    public void setDept(String dept) {
        this.dept = dept;
        if (StringUtils.isNotBlank(dept) && dept.indexOf(",") > 0) {
            this.deptList = Arrays.asList(dept.trim().split(","));
            this.dept = "";
        }
    }

    public String getJobGrade() {
        return jobGrade;
    }

    public void setJobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
        if (StringUtils.isNotBlank(jobGrade) && jobGrade.indexOf(",") > 0) {
            this.jobGradeList = Arrays.asList(jobGrade.trim().split(","));
            this.jobGrade = "";
        }
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
        if (type != null) {
            switch (type) {
                case 1:
                    this.tabType = "type3";
                    break;
                case 2:
                    this.tabType = "type4";
                    break;
                case 3:
                    this.tabType = "type5";
                    break;
                case 4:
                    this.tabType = "type6";
                    break;
                case 5:
                    this.tabType = "type7";
                    break;
                case 6:
                    this.tabType = "type8";
                    break;
                case 7:
                    this.tabType = "type9";
                    break;
                case 8:
                    this.tabType = "type1";
                    break;
                case 9:
                    this.tabType = "type2";
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "CommonSearchParam{" +
                "dept='" + dept + '\'' +
                ", jobGrade='" + jobGrade + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", deptList=" + deptList +
                ", jobGradeList=" + jobGradeList +
                '}';
    }
}
