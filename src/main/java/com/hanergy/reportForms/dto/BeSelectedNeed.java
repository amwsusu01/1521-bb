package com.hanergy.reportForms.dto;

import com.hanergy.reportForms.commons.enums.EnumVerifySource;

/**
 * @ClassName BeSelectedNeed
 * @Description TODO 查询是否需要的属性
 * @Author DURONGHONG
 * @DATE 2018/10/18 14:55
 * @Version 1.0
 **/
public class BeSelectedNeed {


    private Boolean checks = true;
    private EnumVerifySource checkSource;

    private Boolean credentials = true;
    private EnumVerifySource credentialsSource;

    private Boolean education = true;
    private EnumVerifySource educationSource;

    private Boolean experience = true;
    private EnumVerifySource experienceSource;

    private Boolean professinalCertificate = true;
    private EnumVerifySource professinalCertificateSource;

    private Boolean courtProceeds = true;
    private EnumVerifySource courtProceedsSource;

    private Boolean commercialProfit = true;
    private EnumVerifySource commercialProfitSource;

    private Boolean financial = true;
    private EnumVerifySource financialSource;

    private Boolean badRecord = true;
    private EnumVerifySource badRecordSource;


    public Boolean getChecks() {
        return checks;
    }

    public void setChecks(Boolean checks) {
        this.checks = checks;
    }

    public EnumVerifySource getCheckSource() {
        return checkSource;
    }

    public void setCheckSource(EnumVerifySource checkSource) {
        this.checkSource = checkSource;
    }

    public Boolean getCredentials() {
        return credentials;
    }

    public void setCredentials(Boolean credentials) {
        this.credentials = credentials;
    }

    public EnumVerifySource getCredentialsSource() {
        return credentialsSource;
    }

    public void setCredentialsSource(EnumVerifySource credentialsSource) {
        this.credentialsSource = credentialsSource;
    }

    public Boolean getEducation() {
        return education;
    }

    public void setEducation(Boolean education) {
        this.education = education;
    }

    public EnumVerifySource getEducationSource() {
        return educationSource;
    }

    public void setEducationSource(EnumVerifySource educationSource) {
        this.educationSource = educationSource;
    }

    public Boolean getExperience() {
        return experience;
    }

    public void setExperience(Boolean experience) {
        this.experience = experience;
    }

    public EnumVerifySource getExperienceSource() {
        return experienceSource;
    }

    public void setExperienceSource(EnumVerifySource experienceSource) {
        this.experienceSource = experienceSource;
    }

    public Boolean getProfessinalCertificate() {
        return professinalCertificate;
    }

    public void setProfessinalCertificate(Boolean professinalCertificate) {
        this.professinalCertificate = professinalCertificate;
    }

    public EnumVerifySource getProfessinalCertificateSource() {
        return professinalCertificateSource;
    }

    public void setProfessinalCertificateSource(EnumVerifySource professinalCertificateSource) {
        this.professinalCertificateSource = professinalCertificateSource;
    }

    public Boolean getCourtProceeds() {
        return courtProceeds;
    }

    public void setCourtProceeds(Boolean courtProceeds) {
        this.courtProceeds = courtProceeds;
    }

    public EnumVerifySource getCourtProceedsSource() {
        return courtProceedsSource;
    }

    public void setCourtProceedsSource(EnumVerifySource courtProceedsSource) {
        this.courtProceedsSource = courtProceedsSource;
    }

    public Boolean getCommercialProfit() {
        return commercialProfit;
    }

    public void setCommercialProfit(Boolean commercialProfit) {
        this.commercialProfit = commercialProfit;
    }

    public EnumVerifySource getCommercialProfitSource() {
        return commercialProfitSource;
    }

    public void setCommercialProfitSource(EnumVerifySource commercialProfitSource) {
        this.commercialProfitSource = commercialProfitSource;
    }

    public Boolean getFinancial() {
        return financial;
    }

    public void setFinancial(Boolean financial) {
        this.financial = financial;
    }

    public EnumVerifySource getFinancialSource() {
        return financialSource;
    }

    public void setFinancialSource(EnumVerifySource financialSource) {
        this.financialSource = financialSource;
    }

    public Boolean getBadRecord() {
        return badRecord;
    }

    public void setBadRecord(Boolean badRecord) {
        this.badRecord = badRecord;
    }

    public EnumVerifySource getBadRecordSource() {
        return badRecordSource;
    }

    public void setBadRecordSource(EnumVerifySource badRecordSource) {
        this.badRecordSource = badRecordSource;
    }
}
