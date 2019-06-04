package com.hanergy.reportForms.mapper;

import com.hanergy.reportForms.mapper.entity.Project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 实际工作项目 Mapper 接口
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-12
 */
public interface ProjectMapper extends BaseMapper<Project> {
	
	
	int deleteProjcetByIdArr(@Param("expirenceIdArr")List<Long>  expirenceIdArr);

}
