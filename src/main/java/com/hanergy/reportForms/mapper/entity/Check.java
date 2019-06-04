package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 背调
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
@ApiModel(value = "Check对象", description = "背调")
@TableName(value = "check_info")
public class Check extends Model<Check> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "中间方案主键")
    private Long agencyPlanId;

    @ApiModelProperty(value = "候选人表主键")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "订单编码")
    private String orderNumber;

    @ApiModelProperty(value = "报告编码：当核实来源为“中介”，此项要填写")
    private String reportNumber;

    @ApiModelProperty(value = "核实来源： 2人力    3中介")
    private Integer verifySource;

    @ApiModelProperty(value = "核实单位：来源为人力则为用户表主键，来源为中介则为中介表主键")
    private Long verifyUnit;

    @ApiModelProperty(value = "核实单位名称")
    private String verifyName;

    @ApiModelProperty(value = "核实人员")
    private String verifyPerson;

    @ApiModelProperty(value = "核实说明")
    private String verifyExplain;

    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯")
    private Integer verifyLevel;

    @ApiModelProperty(value = "核实状态：1终版    2初版")
    private Integer verifyStatus;

    @ApiModelProperty(value = "核实日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date verifyDate;

    @ApiModelProperty(value = "审核人员")
    private Long examine;

    @ApiModelProperty(value = "提交日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date submitDate;

    @ApiModelProperty(value = "中版日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date middleDate;

    @ApiModelProperty(value = "完成日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date completeDate;

    @ApiModelProperty(value = "背调项目")
    @TableField(exist = false)
    private List<CheckProject> checkProjects;

    @ApiModelProperty(value = "委托日期")
    private Date entrustDate;

    @ApiModelProperty(value = "报告文件列表")
    @TableField(exist = false)
    private List<Document> documents;


    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Date getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgencyPlanId() {
        return agencyPlanId;
    }

    public void setAgencyPlanId(Long agencyPlanId) {
        this.agencyPlanId = agencyPlanId;
    }

    public Long getBeSelected() {
        return beSelected;
    }

    public void setBeSelected(Long beSelected) {
        this.beSelected = beSelected;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public Integer getVerifySource() {
        return verifySource;
    }

    public void setVerifySource(Integer verifySource) {
        this.verifySource = verifySource;
    }

    public Long getVerifyUnit() {
        return verifyUnit;
    }

    public void setVerifyUnit(Long verifyUnit) {
        this.verifyUnit = verifyUnit;
    }

    public String getVerifyName() {
        return verifyName;
    }

    public void setVerifyName(String verifyName) {
        this.verifyName = verifyName;
    }

    public String getVerifyPerson() {
        return verifyPerson;
    }

    public void setVerifyPerson(String verifyPerson) {
        this.verifyPerson = verifyPerson;
    }

    public String getVerifyExplain() {
        return verifyExplain;
    }

    public void setVerifyExplain(String verifyExplain) {
        this.verifyExplain = verifyExplain;
    }

    public Integer getVerifyLevel() {
        return verifyLevel;
    }

    public void setVerifyLevel(Integer verifyLevel) {
        this.verifyLevel = verifyLevel;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public Long getExamine() {
        return examine;
    }

    public void setExamine(Long examine) {
        this.examine = examine;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getMiddleDate() {
        return middleDate;
    }

    public void setMiddleDate(Date middleDate) {
        this.middleDate = middleDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public List<CheckProject> getCheckProjects() {
        return checkProjects;
    }

    public void setCheckProjects(List<CheckProject> checkProjects) {
        this.checkProjects = checkProjects;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
