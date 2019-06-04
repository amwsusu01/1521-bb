package com.hanergy.reportForms.mapper;

import com.hanergy.reportForms.entity.entrust.report.CheckProjectInfo;
import com.hanergy.reportForms.mapper.entity.CheckProject;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-09
 */
public interface CheckProjectMapper extends BaseMapper<CheckProject> {
	
		List<CheckProjectInfo>  getCheckProjectInfoByCheckId(@Param("checkId")Long checkId );
}
