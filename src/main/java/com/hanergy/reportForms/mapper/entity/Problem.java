package com.hanergy.reportForms.mapper.entity;

import com.hanergy.reportForms.entity.log.SystemLog;

import java.util.Date;

public class Problem extends SystemLog{
    private String tichubumen;//提出部门
    private String tichuren;//提出人
    private String tichubumenfuzeren;//提出部门负责人
    private String shenqingriqi;//提出时间
    private Date applyStartDate;//申请开始日期
    private Date applyEndDate;//申请结束日期
    private String wentileixing;//问题类型
    private String chengbanrenquerenwentizhuan;//问题状态
    private String wentimiaoshu;//问题描述
    private String jianyi;//建议
    private String chengbanbumen;//承办部门
    private String chengbanbumenfuzeren;//承办部门负责人
    private String chengbanren;//承办人
    private String wanchengshijian;//完成时间
    private Date finishStartDate; //完成开始如期
    private Date finishEndDate;//完成结束日期
    private String cuoshijishuoming;//实施措施及结果说明
    private Integer pageNo;
    private Integer pageSize;
    private boolean isNo;
    public String getTichubumen() {
        return tichubumen;
    }

    public void setTichubumen(String tichubumen) {
        this.tichubumen = tichubumen;
    }

    public String getTichuren() {
        return tichuren;
    }

    public void setTichuren(String tichuren) {
        this.tichuren = tichuren;
    }

    public String getTichubumenfuzeren() {
        return tichubumenfuzeren;
    }

    public void setTichubumenfuzeren(String tichubumenfuzeren) {
        this.tichubumenfuzeren = tichubumenfuzeren;
    }

    public String getShenqingriqi() {
        return shenqingriqi;
    }

    public void setShenqingriqi(String shenqingriqi) {
        this.shenqingriqi = shenqingriqi;
    }

    public Date getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(Date applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public Date getApplyEndDate() {
        return applyEndDate;
    }

    public void setApplyEndDate(Date applyEndDate) {
        this.applyEndDate = applyEndDate;
    }

    public String getWentileixing() {
        return wentileixing;
    }

    public void setWentileixing(String wentileixing) {
        this.wentileixing = wentileixing;
    }

    public String getChengbanrenquerenwentizhuan() {
        return chengbanrenquerenwentizhuan;
    }

    public void setChengbanrenquerenwentizhuan(String chengbanrenquerenwentizhuan) {
        this.chengbanrenquerenwentizhuan = chengbanrenquerenwentizhuan;
    }

    public String getWentimiaoshu() {
        return wentimiaoshu;
    }

    public void setWentimiaoshu(String wentimiaoshu) {
        this.wentimiaoshu = wentimiaoshu;
    }

    public String getJianyi() {
        return jianyi;
    }

    public void setJianyi(String jianyi) {
        this.jianyi = jianyi;
    }

    public String getChengbanbumen() {
        return chengbanbumen;
    }

    public void setChengbanbumen(String chengbanbumen) {
        this.chengbanbumen = chengbanbumen;
    }

    public String getChengbanbumenfuzeren() {
        return chengbanbumenfuzeren;
    }

    public void setChengbanbumenfuzeren(String chengbanbumenfuzeren) {
        this.chengbanbumenfuzeren = chengbanbumenfuzeren;
    }

    public String getChengbanren() {
        return chengbanren;
    }

    public void setChengbanren(String chengbanren) {
        this.chengbanren = chengbanren;
    }

    public String getWanchengshijian() {
        return wanchengshijian;
    }

    public void setWanchengshijian(String wanchengshijian) {
        this.wanchengshijian = wanchengshijian;
    }

    public Date getFinishStartDate() {
        return finishStartDate;
    }

    public void setFinishStartDate(Date finishStartDate) {
        this.finishStartDate = finishStartDate;
    }

    public Date getFinishEndDate() {
        return finishEndDate;
    }

    public void setFinishEndDate(Date finishEndDate) {
        this.finishEndDate = finishEndDate;
    }

    public String getCuoshijishuoming() {
        return cuoshijishuoming;
    }

    public void setCuoshijishuoming(String cuoshijishuoming) {
        this.cuoshijishuoming = cuoshijishuoming;
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
    public boolean getIsNo() {
        return isNo;
    }

    public void setIsNo(boolean isNo) {
        this.isNo = isNo;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "tichubumen='" + tichubumen + '\'' +
                ", tichuren='" + tichuren + '\'' +
                ", tichubumenfuzeren='" + tichubumenfuzeren + '\'' +
                ", shenqingriqi='" + shenqingriqi + '\'' +
                ", applyStartDate=" + applyStartDate +
                ", applyEndDate=" + applyEndDate +
                ", wentileixing='" + wentileixing + '\'' +
                ", chengbanrenquerenwentizhuan='" + chengbanrenquerenwentizhuan + '\'' +
                ", wentimiaoshu='" + wentimiaoshu + '\'' +
                ", jianyi='" + jianyi + '\'' +
                ", chengbanbumen='" + chengbanbumen + '\'' +
                ", chengbanbumenfuzeren='" + chengbanbumenfuzeren + '\'' +
                ", chengbanren='" + chengbanren + '\'' +
                ", wanchengshijian='" + wanchengshijian + '\'' +
                ", finishStartDate=" + finishStartDate +
                ", finishEndDate=" + finishEndDate +
                ", cuoshijishuoming='" + cuoshijishuoming + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", isNo=" + isNo +
                '}';
    }
}
