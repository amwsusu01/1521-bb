package com.hanergy.reportForms.dto.third;

/**
 * @ClassName MediEducation
 * @Description TODO 教育经历(中介使用)
 * @Author DURONGHONG
 * @DATE 2018/10/9 16:55
 * @Version 1.0
 **/
public class MediEducation {
    // 学校
    private String school;
    // 入学日期
    private String entryDate;
    // 毕业日期
    private String leaveDate;
    // 专业
    private String major;
    // 学历
    private Integer education;
    // 学历性质
    private Integer educationType;
    // 学位
    private String degree;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getEducationType() {
        return educationType;
    }

    public void setEducationType(Integer educationType) {
        this.educationType = educationType;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }
}
