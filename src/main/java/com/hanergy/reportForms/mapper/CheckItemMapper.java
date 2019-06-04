package com.hanergy.reportForms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanergy.reportForms.entity.template.CheckItemEntity;
import com.hanergy.reportForms.mapper.entity.CheckItem;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 背调项目 Mapper 接口
 * </p>
 *
 * @author fangshuai
 * @since 2018-09-20
 */
public interface CheckItemMapper extends BaseMapper<CheckItem> {
	
	List<CheckItem> getItemListByTemplateId(@Param("templateId")Long templateId);

    List<CheckItemEntity> getItemListByAgencyId(@Param("agencyId") Long agencyId);
}
