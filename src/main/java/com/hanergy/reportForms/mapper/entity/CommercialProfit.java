package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 商业利益
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-12
 */
@ApiModel(value="CommercialProfit对象", description="商业利益")
public class CommercialProfit extends Model<CommercialProfit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "候选人（表的主键）")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "来源：1候选人   2人力    3中介背调")
    private Integer verifySource;

    @ApiModelProperty(value = "背调（表的主键）")
    private Long checkId;

    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯")
    private Integer verifyLevel;

    @ApiModelProperty(value = "核实说明")
    private String verifyExplain;

    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "企业类型")
    private String enterpriseType;

    @ApiModelProperty(value = "执照编号")
    private String licenseNum;

    @ApiModelProperty(value = "成立时间（注册时间)")
    private String establishTime;

    @ApiModelProperty(value = "法定代表人")
    private String legalRepresentative;

    @ApiModelProperty(value = "注册资本(单位：元)")
    private String registeredCapital;

    @ApiModelProperty(value = "执行董事")
    private String executiveDirector;

    @ApiModelProperty(value = "经理")
    private String manager;

    @ApiModelProperty(value = "等级状态")
    private String registrationState;

    @ApiModelProperty(value = "变更信息")
    private String changeMessage;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getBeSelected() {
        return beSelected;
    }

    public void setBeSelected(Long beSelected) {
        this.beSelected = beSelected;
    }
    public Integer getVerifySource() {
        return verifySource;
    }

    public void setVerifySource(Integer verifySource) {
        this.verifySource = verifySource;
    }
    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }
    public Integer getVerifyLevel() {
        return verifyLevel;
    }

    public void setVerifyLevel(Integer verifyLevel) {
        this.verifyLevel = verifyLevel;
    }
    public String getVerifyExplain() {
        return verifyExplain;
    }

    public void setVerifyExplain(String verifyExplain) {
        this.verifyExplain = verifyExplain;
    }
    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }
    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }
    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }
    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }
    public String getExecutiveDirector() {
        return executiveDirector;
    }

    public void setExecutiveDirector(String executiveDirector) {
        this.executiveDirector = executiveDirector;
    }
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    public String getRegistrationState() {
        return registrationState;
    }

    public void setRegistrationState(String registrationState) {
        this.registrationState = registrationState;
    }
    public String getChangeMessage() {
        return changeMessage;
    }

    public void setChangeMessage(String changeMessage) {
        this.changeMessage = changeMessage;
    }
    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }
    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CommercialProfit{" +
        "id=" + id +
        ", beSelected=" + beSelected +
        ", verifySource=" + verifySource +
        ", checkId=" + checkId +
        ", verifyLevel=" + verifyLevel +
        ", verifyExplain=" + verifyExplain +
        ", enterpriseName=" + enterpriseName +
        ", enterpriseType=" + enterpriseType +
        ", licenseNum=" + licenseNum +
        ", establishTime=" + establishTime +
        ", legalRepresentative=" + legalRepresentative +
        ", registeredCapital=" + registeredCapital +
        ", executiveDirector=" + executiveDirector +
        ", manager=" + manager +
        ", registrationState=" + registrationState +
        ", changeMessage=" + changeMessage +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        "}";
    }
}
