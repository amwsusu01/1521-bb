package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-23
 */
@ApiModel(value="Document对象", description="文件表")
public class Document extends Model<Document> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "文件原始名称")
    private String name;

    @ApiModelProperty(value = "文件存放地址")
    private String url;

    @ApiModelProperty(value = "[业务]文件类型")
    private Integer type;

    @ApiModelProperty(value = "上传时间")
    private Date createtime;

    @ApiModelProperty(value = "业务id")
    private Long serviceId;

    @ApiModelProperty(value = "排序(根据业务需要自定义)")
    private Integer orderNum;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Document{" +
        "id=" + id +
        ", name=" + name +
        ", url=" + url +
        ", type=" + type +
        ", createtime=" + createtime +
        ", serviceId=" + serviceId +
        ", orderNum=" + orderNum +
        "}";
    }
}
