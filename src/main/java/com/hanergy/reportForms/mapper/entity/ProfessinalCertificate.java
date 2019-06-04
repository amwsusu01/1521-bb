package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 专业资格认证
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-12
 */
@ApiModel(value="ProfessinalCertificate对象", description="专业资格认证")
public class ProfessinalCertificate extends Model<ProfessinalCertificate> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "候选人id")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "核实来源：1候选人   2人力    3中介")
    private Integer verifySource;

    @ApiModelProperty(value = "背调表id")
    private Long checkId;

    @ApiModelProperty(value = "认证内容")
    private String qualificationAttained;

    @ApiModelProperty(value = "认证/发证机构")
    private String institution;

    @ApiModelProperty(value = "认证时间")
    private String qualifiedDate;

    @ApiModelProperty(value = "有效期")
    private String validity;

    @ApiModelProperty(value = "证书编号")
    private String certificateNumber;

    @ApiModelProperty(value = "认证/发证机构联系方式")
    private String institutionContact;

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
    public String getQualificationAttained() {
        return qualificationAttained;
    }

    public void setQualificationAttained(String qualificationAttained) {
        this.qualificationAttained = qualificationAttained;
    }
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
    public String getQualifiedDate() {
        return qualifiedDate;
    }

    public void setQualifiedDate(String qualifiedDate) {
        this.qualifiedDate = qualifiedDate;
    }
    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
    public String getInstitutionContact() {
        return institutionContact;
    }

    public void setInstitutionContact(String institutionContact) {
        this.institutionContact = institutionContact;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProfessinalCertificate{" +
        "id=" + id +
        ", beSelected=" + beSelected +
        ", verifySource=" + verifySource +
        ", checkId=" + checkId +
        ", qualificationAttained=" + qualificationAttained +
        ", institution=" + institution +
        ", qualifiedDate=" + qualifiedDate +
        ", validity=" + validity +
        ", certificateNumber=" + certificateNumber +
        ", institutionContact=" + institutionContact +
        "}";
    }
}
