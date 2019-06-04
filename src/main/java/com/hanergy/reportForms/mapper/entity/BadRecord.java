package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 不良记录
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-12
 */
@ApiModel(value="BadRecord对象", description="不良记录")
public class BadRecord extends Model<BadRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "候选人（表的主键）")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "来源：1候选人   2人力    3中介")
    private Integer verifySource;

    @ApiModelProperty(value = "背调（表的主键）")
    private Long checkId;

    @ApiModelProperty(value = "派出所证明：1有   0无")
    private Integer evidence;

    @ApiModelProperty(value = "派出所证明号码：有则填此项")
    @TableField("evidenceNo")
    private String evidenceNo;

    @ApiModelProperty(value = "犯罪证明：1有   0无")
    private Integer crime;

    @ApiModelProperty(value = "犯罪证明日期：有则填此项")
    @TableField("crimeDate")
    private String crimeDate;

    @ApiModelProperty(value = "是否违法违纪: 1 : 是 ；0 ：否")
    private Integer violationDiscipline;

    @ApiModelProperty(value = "违法违纪说明")
    private String violationDisciplineExplain;

    @ApiModelProperty(value = "其它说明")
    @TableField("otherDescription")
    private String otherDescription;

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
    public Integer getEvidence() {
        return evidence;
    }

    public void setEvidence(Integer evidence) {
        this.evidence = evidence;
    }
    public String getEvidenceNo() {
        return evidenceNo;
    }

    public void setEvidenceNo(String evidenceNo) {
        this.evidenceNo = evidenceNo;
    }
    public Integer getCrime() {
        return crime;
    }

    public void setCrime(Integer crime) {
        this.crime = crime;
    }
 
    public String getCrimeDate() {
		return crimeDate;
	}

	public void setCrimeDate(String crimeDate) {
		this.crimeDate = crimeDate;
	}

	public Integer getViolationDiscipline() {
        return violationDiscipline;
    }

    public void setViolationDiscipline(Integer violationDiscipline) {
        this.violationDiscipline = violationDiscipline;
    }
    public String getViolationDisciplineExplain() {
        return violationDisciplineExplain;
    }

    public void setViolationDisciplineExplain(String violationDisciplineExplain) {
        this.violationDisciplineExplain = violationDisciplineExplain;
    }
    public String getOtherDescription() {
        return otherDescription;
    }

    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription;
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
        return "BadRecord{" +
        "id=" + id +
        ", beSelected=" + beSelected +
        ", verifySource=" + verifySource +
        ", checkId=" + checkId +
        ", evidence=" + evidence +
        ", evidenceNo=" + evidenceNo +
        ", crime=" + crime +
        ", crimeDate=" + crimeDate +
        ", violationDiscipline=" + violationDiscipline +
        ", violationDisciplineExplain=" + violationDisciplineExplain +
        ", otherDescription=" + otherDescription +
        ", createtime=" + createtime +
        ", updatetime=" + updatetime +
        "}";
    }
}
