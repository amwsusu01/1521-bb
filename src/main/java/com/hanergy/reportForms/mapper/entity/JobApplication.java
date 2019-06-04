package com.hanergy.reportForms.mapper.entity;

import com.hanergy.reportForms.commons.CommonSearchParam;
import com.hanergy.reportForms.entity.log.SystemLog;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class JobApplication extends CommonSearchParam {
    private String approver;//负责人
    private List<String>approverList;
    private String recruiter;//招聘负责人
    private List<String>recruiterList;
    private String hrvp;//HRVP
    private List<String>hrvpList;
    private Integer candidateId;//候选人ID
    private String candidateName;//候选人姓名
    private List<String> candidateNameList;
    private String positionNumber;//候选人工号
    private List<String>positionNumberList;
    private String nationalidcardmun;//候选人身份证
    private String jobTitle;//应聘职位
    private String jobLevel;//推荐职级
    private String buoncode;//应聘的集团
    private List<String>buoncodeList;
    private String legalEntitycode;//法人实体
    private String divicode;//应聘的部门/事业部
    private List<String>divicodeList;
    private String busUnit;//业务单元
    private List<String>busUnitList;
    private String locationcode;//工作地点
    private String hiringManager;//入职后汇报领导
    private String leaderShip;//分管领导
    private String resumeSource;//简历来源
    private String talkSalaryDate;//谈薪日期
    private String offerDate;//offer日期
    private String jobStartDate;//入职开始日期
    private String jobEndDate;//入职结束日期
    private String talkStartDate;//谈薪开始日期
    private String talkEndDate;//谈薪结束日期
    private String offerStartDate;//offer开始日期
    private String offerEndDate;//offer结束日期
    private String jobDate;//入职日期
    private String explanJobDate;//预计入职时间
    private String create_date;
    public JobApplication(){}

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getLegalEntitycode() {
        return legalEntitycode;
    }

    public void setLegalEntitycode(String legalEntitycode) {
        this.legalEntitycode = legalEntitycode;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
        if (StringUtils.isNotBlank(approver)&&approver.indexOf(",")>0){
            String[] array = approver.trim().split(",");
            this.approverList = Arrays.asList(array);
            this.approver="";
        }
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
        if (StringUtils.isNotBlank(recruiter)&&recruiter.indexOf(",")>0){
            this.recruiterList=Arrays.asList(recruiter.trim().split(","));
            this.recruiter ="";
        }
    }

    public String getHrvp() {
        return hrvp;
    }

    public void setHrvp(String hrvp) {
        this.hrvp = hrvp;
        if (StringUtils.isNotBlank(hrvp)&&hrvp.indexOf(",")>0){
            this.hrvpList=Arrays.asList(hrvp.trim().split(","));
            this.hrvp="";
        }
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName.trim();
        if (StringUtils.isNotBlank(candidateName)&&candidateName.indexOf(",")>0){
            this.candidateNameList=Arrays.asList(candidateName.trim().split(","));
        }
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
        if (StringUtils.isNotBlank(positionNumber)&&positionNumber.indexOf(",")>0){
            this.positionNumberList=Arrays.asList(positionNumber.trim().split(","));
        }
    }

    public String getNationalidcardmun() {
        return nationalidcardmun;
    }

    public void setNationalidcardmun(String nationalidcardmun) {
        this.nationalidcardmun = nationalidcardmun;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getBuoncode() {
        return buoncode;
    }

    public void setBuoncode(String buoncode) {
        this.buoncode = buoncode;
        if (StringUtils.isNotBlank(buoncode)&&buoncode.indexOf(",")>0){
            this.buoncodeList=Arrays.asList(buoncode.trim().split(","));
            this.buoncode="";
        }
    }

    public String getDivicode() {
        return divicode;
    }

    public void setDivicode(String divicode) {
        this.divicode = divicode;
        if (StringUtils.isNotBlank(divicode)&&divicode.indexOf(",")>0){
            this.divicodeList=Arrays.asList(divicode.trim().split(","));
            this.divicode="";
        }
    }

    public String getBusUnit() {
        return busUnit;
    }

    public void setBusUnit(String busUnit) {
        this.busUnit = busUnit;
        if (StringUtils.isNotBlank(busUnit)&&busUnit.indexOf(",")>0){
            this.busUnitList=Arrays.asList(busUnit.trim().split(","));
            this.busUnit="";
        }
    }

    public String getLocationcode() {
        return locationcode;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode;
    }

    public String getHiringManager() {
        return hiringManager;
    }

    public void setHiringManager(String hiringManager) {
        this.hiringManager = hiringManager;
    }

    public String getLeaderShip() {
        return leaderShip;
    }

    public void setLeaderShip(String leaderShip) {
        this.leaderShip = leaderShip;
    }

    public String getResumeSource() {
        return resumeSource;
    }

    public void setResumeSource(String resumeSource) {
        this.resumeSource = resumeSource;
    }

    public String getTalkSalaryDate() {
        return talkSalaryDate;
    }

    public void setTalkSalaryDate(String talkSalaryDate) {
        this.talkSalaryDate = talkSalaryDate;
    }

    public String getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(String offerDate) {
        this.offerDate = offerDate;
    }

    public String getExplanJobDate() {
        return explanJobDate;
    }

    public void setExplanJobDate(String explanJobDate) {
        this.explanJobDate = explanJobDate;
    }

    public String getJobStartDate() {
        return jobStartDate;
    }

    public void setJobStartDate(String jobStartDate) {
        this.jobStartDate = jobStartDate;
    }

    public String getJobEndDate() {
        return jobEndDate;
    }

    public void setJobEndDate(String jobEndDate) {
        this.jobEndDate = jobEndDate;
    }

    public String getTalkStartDate() {
        return talkStartDate;
    }

    public void setTalkStartDate(String talkStartDate) {
        this.talkStartDate = talkStartDate;
    }

    public String getTalkEndDate() {
        return talkEndDate;
    }

    public void setTalkEndDate(String talkEndDate) {
        this.talkEndDate = talkEndDate;
    }

    public String getOfferStartDate() {
        return offerStartDate;
    }

    public void setOfferStartDate(String offerStartDate) {
        this.offerStartDate = offerStartDate;
    }

    public String getOfferEndDate() {
        return offerEndDate;
    }

    public void setOfferEndDate(String offerEndDate) {
        this.offerEndDate = offerEndDate;
    }

    public String getJobDate() {
        return jobDate;
    }

    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    public List<String> getApproverList() {
        return approverList;
    }

    public List<String> getRecruiterList() {
        return recruiterList;
    }

    public List<String> getHrvpList() {
        return hrvpList;
    }

    public List<String> getCandidateNameList() {
        return candidateNameList;
    }

    public List<String> getPositionNumberList() {
        return positionNumberList;
    }

    public List<String> getBuoncodeList() {
        return buoncodeList;
    }

    public List<String> getDivicodeList() {
        return divicodeList;
    }

    public List<String> getBusUnitList() {
        return busUnitList;
    }

}
