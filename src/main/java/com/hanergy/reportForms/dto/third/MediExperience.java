package com.hanergy.reportForms.dto.third;

/**
 * @ClassName MediExperience
 * @Description TODO 中介使用--->> 工作经历
 * @Author DURONGHONG
 * @DATE 2018/10/9 16:55
 * @Version 1.0
 **/
public class MediExperience {
    // 公司名
    private String company;
    // 入职日期
    private String entryDate;
    // 离职日期
    private String leaveDate;
    // 部门
    private String department;
    // 职位
    private String job;
    // 职责
    private String duty;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
}
