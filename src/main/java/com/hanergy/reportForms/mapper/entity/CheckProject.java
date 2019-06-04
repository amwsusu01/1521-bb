package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 背调项目
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-10
 */
@ApiModel(value="CheckProject对象", description="背调项目")
public class CheckProject extends Model<CheckProject> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "项目id")
    @TableField("checkItem_id")
    private Long checkitemId;

    @ApiModelProperty(value = "背调（表的主键）")
    private Long checkId;
    
    @ApiModelProperty(value = "审核内容")
    private String verifyContent;
    
    @ApiModelProperty(value = "核实来源：1候选人   2人力    3中介")
    private Integer verifySource;

    @ApiModelProperty(value = "核实说明")
    private String verifyExplain;

    @ApiModelProperty(value = "核实等级：1红灯   2黄灯   3蓝灯   4绿灯")
    private Integer verifyLevel;

    @ApiModelProperty(value = "调查日期")
    private Date investigationTime;

    @ApiModelProperty(value = "证明机构")
    private String certificationBody;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCheckitemId() {
        return checkitemId;
    }

    public void setCheckitemId(Long checkitemId) {
        this.checkitemId = checkitemId;
    }
    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }
    
    public String getVerifyContent() {
		return verifyContent;
	}

	public void setVerifyContent(String verifyContent) {
		this.verifyContent = verifyContent;
	}

	public Integer getVerifySource() {
        return verifySource;
    }

    public void setVerifySource(Integer verifySource) {
        this.verifySource = verifySource;
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
   
    public Date getInvestigationTime() {
		return investigationTime;
	}

	public void setInvestigationTime(Date investigationTime) {
		this.investigationTime = investigationTime;
	}

	public String getCertificationBody() {
        return certificationBody;
    }

    public void setCertificationBody(String certificationBody) {
        this.certificationBody = certificationBody;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CheckProject{" +
        "id=" + id +
        ", checkitemId=" + checkitemId +
        ", checkId=" + checkId +
        ", verifySource=" + verifySource +
        ", verifyExplain=" + verifyExplain +
        ", verifyLevel=" + verifyLevel +
        ", investigationTime=" + investigationTime +
        ", certificationBody=" + certificationBody +
        "}";
    }
}
