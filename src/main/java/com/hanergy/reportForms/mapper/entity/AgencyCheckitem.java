package com.hanergy.reportForms.mapper.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * agency与checkItem的中间表
 * </p>
 *
 * @author duronghong
 * @since 2018-09-28
 */
@TableName("agency_check_item")
@ApiModel(value="AgencyCheckitem对象", description="agency与checkItem的中间表")
public class AgencyCheckitem extends Model<AgencyCheckitem> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "中介id")
    private Long agency;

    @ApiModelProperty(value = "项目Id")
    private Long checkItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getAgency() {
        return agency;
    }

    public void setAgency(Long agency) {
        this.agency = agency;
    }
    public Long getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(Long checkItem) {
        this.checkItem = checkItem;
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
