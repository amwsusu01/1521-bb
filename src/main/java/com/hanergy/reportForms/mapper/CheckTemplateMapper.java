package com.hanergy.reportForms.mapper;

import com.hanergy.reportForms.mapper.entity.AgencyPlan;
import com.hanergy.reportForms.mapper.entity.CheckTemplate;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 方案模板 Mapper 接口
 * </p>
 *
 * @author fangshuai
 * @since 2018-09-27
 */
public interface CheckTemplateMapper extends BaseMapper<CheckTemplate> {
	
	boolean  insertBacthRelation(@Param("prods")List<CheckTemplate> list);	
	
	int  deleteByIdList(@Param("planList")List<AgencyPlan> planList);
}
