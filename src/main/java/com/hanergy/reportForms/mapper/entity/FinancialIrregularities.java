package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 金融违规
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-10
 */
@ApiModel(value="FinancialIrregularities对象", description="金融违规")
public class FinancialIrregularities extends Model<FinancialIrregularities> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "候选人id")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "核实来源：1候选人   2人力    3中介")
    private Integer verifySource;

    @ApiModelProperty(value = "背调id")
    private Long checkId;

    @ApiModelProperty(value = "调查机构")
    private String investigativeAgency;

    @ApiModelProperty(value = "调查结果")
    private String investigativeResults;

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
    public String getInvestigativeAgency() {
        return investigativeAgency;
    }

    public void setInvestigativeAgency(String investigativeAgency) {
        this.investigativeAgency = investigativeAgency;
    }
    public String getInvestigativeResults() {
        return investigativeResults;
    }

    public void setInvestigativeResults(String investigativeResults) {
        this.investigativeResults = investigativeResults;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FinancialIrregularities{" +
        "id=" + id +
        ", beSelected=" + beSelected +
        ", verifySource=" + verifySource +
        ", checkId=" + checkId +
        ", investigativeAgency=" + investigativeAgency +
        ", investigativeResults=" + investigativeResults +
        "}";
    }
}
