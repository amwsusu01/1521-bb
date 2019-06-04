package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-16
 */
@ApiModel(value="CheckStatistics对象", description="")
public class CheckStatistics extends Model<CheckStatistics> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "背调状态")
    private Integer checkStatus;

    @ApiModelProperty(value = "统计总数")
    private Integer statistics;
    
    @ApiModelProperty(value = "用户id")
    private Long userId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }
    public Integer getStatistics() {
        return statistics;
    }

    public void setStatistics(Integer statistics) {
        this.statistics = statistics;
    }

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    
}
