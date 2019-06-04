package com.hanergy.reportForms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanergy.reportForms.dto.third.MediTemplate;
import com.hanergy.reportForms.entity.template.CheckItemEntity;
import com.hanergy.reportForms.entity.template.TemplateInfo;
import com.hanergy.reportForms.mapper.entity.Template;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 背调方案 Mapper 接口
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
public interface TemplateMapper extends BaseMapper<Template> {
    List<TemplateInfo> queryTemplateList(@Param("start") Integer start, 
    		@Param("end") Integer end);

    int updateTemplateById(@Param("template") Template template);

    TemplateInfo getTemplateInfoById(@Param("id") Long id);

    MediTemplate getMediaTemplateById(@Param("templateId") Long templateId);

    List<CheckItemEntity> getCheckItemsByAgencyPlanId(@Param("agencyPlanId") Long agencyPlanId);
    /**
     * 新增获取方案及其对应的检查项（排序）
     * @param start
     * @param end
     * @return
     */
    List<TemplateInfo> queryTemplateOrderList(@Param("start") Integer start, 
    		@Param("end") Integer end);
}	
