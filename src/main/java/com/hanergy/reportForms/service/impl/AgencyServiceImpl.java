package com.hanergy.reportForms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanergy.reportForms.mapper.AgencyMapper;
import com.hanergy.reportForms.mapper.entity.Agency;
import com.hanergy.reportForms.mapper.entity.User;
import com.hanergy.reportForms.service.IAgencyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 中介 就是需求中的“供应商” 服务实现类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
@Service
public class AgencyServiceImpl extends ServiceImpl<AgencyMapper, Agency> implements IAgencyService {

    @Override
    public IPage<Agency> queryPage(Wrapper<Agency> userWrapper, Integer pageNo, Integer pageSize) {
        Page<Agency> page = new Page<Agency>(pageNo, pageSize);
        IPage<Agency> iPage = baseMapper.selectPage(page, userWrapper);
        return iPage;
    }
}
