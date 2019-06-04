package com.hanergy.reportForms.entity.template;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;


@ApiModel(value="检查项实体类", description="检查项实体类")
public class CheckItemEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "检查项目id")
	private Long id ;
	
	@ApiModelProperty(value = "检查项目编码")
	private String code ;
	
	@ApiModelProperty(value = "检查项名称")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CheckItemEntity that = (CheckItemEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
