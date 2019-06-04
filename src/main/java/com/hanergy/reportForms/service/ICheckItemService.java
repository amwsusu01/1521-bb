package com.hanergy.reportForms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanergy.reportForms.entity.template.CheckItemEntity;
import com.hanergy.reportForms.mapper.entity.CheckItem;

import java.util.List;

/**
 * <p>
 * 背调项目 服务类
 * </p>
 *
 * @author fangshuai
 * @since 2018-09-20
 */
public interface ICheckItemService extends IService<CheckItem> {


    List<CheckItem> getItemListByTemplateId(Long TemplateId);

    /**
     * 通过中介id查询中介公司拥有的查询项目
     *
     * @param agencyId
     * @return
     */
    List<CheckItemEntity> getItemListByAgencyId(Long agencyId);
}
