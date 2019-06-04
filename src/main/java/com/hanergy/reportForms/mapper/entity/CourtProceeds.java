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
 * 
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-09
 */
@ApiModel(value="CourtProceeds对象", description="法院诉讼")
public class CourtProceeds extends Model<CourtProceeds> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "候选人（表的主键）")
    @TableField("beSelected")
    private Long beSelected;

    @ApiModelProperty(value = "来源：1候选人   2人力    3中介背调")
    private Integer verifySource;

    @ApiModelProperty(value = "背调id")
    private Long checkId;

    @ApiModelProperty(value = "诉讼情况：0无记录  1有诉讼记录无详细记录  2有诉讼记录有详细记录")
    private Integer litigationSituation;

    @ApiModelProperty(value = "诉讼说明")
    private String litigationExplain;

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
    public Integer getLitigationSituation() {
        return litigationSituation;
    }

    public void setLitigationSituation(Integer litigationSituation) {
        this.litigationSituation = litigationSituation;
    }
  
    public String getLitigationExplain() {
		return litigationExplain;
	}

	public void setLitigationExplain(String litigationExplain) {
		this.litigationExplain = litigationExplain;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CourtProceeds{" +
        "id=" + id +
        ", beSelected=" + beSelected +
        ", verifySource=" + verifySource +
        ", checkId=" + checkId +
        ", litigationSituation=" + litigationSituation +
        ", litigationExplain=" + litigationExplain +
        "}";
    }
}
