package com.hanergy.reportForms.mapper;

import com.hanergy.reportForms.mapper.entity.AgencyPlan;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fangshuai
 * @since 2018-09-27
 */
public interface AgencyPlanMapper extends BaseMapper<AgencyPlan> {

	boolean insertBacthRelation(@Param("prods")List<AgencyPlan> agencyRelation);

}
