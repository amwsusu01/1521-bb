package com.hanergy.reportForms.service.impl;

import com.hanergy.reportForms.mapper.AgencyCheckitemMapper;
import com.hanergy.reportForms.mapper.entity.AgencyCheckitem;
import com.hanergy.reportForms.service.IAgencyCheckitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * agency与checkItem的中间表 服务实现类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-28
 */
@Service
public class AgencyCheckitemServiceImpl extends ServiceImpl<AgencyCheckitemMapper, AgencyCheckitem> implements IAgencyCheckitemService {

}
