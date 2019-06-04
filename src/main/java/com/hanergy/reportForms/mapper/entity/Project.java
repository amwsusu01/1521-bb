package com.hanergy.reportForms.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 实际工作项目
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-12
 */
@ApiModel(value="Project对象", description="实际工作项目")
public class Project extends Model<Project> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @ApiModelProperty(value = "工作履历表主键")
    private Long experience;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目开始日期")
    private String start;

    @ApiModelProperty(value = "项目结束日期")
    private String end;

    @ApiModelProperty(value = "项目简介")
    private String shortDesc;

    @ApiModelProperty(value = "项目职责")
    private String duty;

    @ApiModelProperty(value = "项目业绩")
    private String performance;

    @ApiModelProperty(value = "用到的技术")
    private String technology;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Project{" +
        "id=" + id +
        ", experience=" + experience +
        ", name=" + name +
        ", start=" + start +
        ", end=" + end +
        ", shortDesc=" + shortDesc +
        ", duty=" + duty +
        ", performance=" + performance +
        ", technology=" + technology +
        "}";
    }
}
