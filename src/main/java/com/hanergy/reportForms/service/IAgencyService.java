package com.hanergy.reportForms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanergy.reportForms.mapper.entity.Agency;
import com.hanergy.reportForms.mapper.entity.User;

/**
 * <p>
 * 中介 就是需求中的“供应商” 服务类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
public interface IAgencyService extends IService<Agency> {

    IPage<Agency> queryPage(Wrapper<Agency> userWrapper, Integer page, Integer pageSize);

}
