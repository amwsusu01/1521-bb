package com.hanergy.reportForms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanergy.reportForms.entity.template.CheckItemEntity;
import com.hanergy.reportForms.mapper.CheckItemMapper;
import com.hanergy.reportForms.mapper.entity.CheckItem;
import com.hanergy.reportForms.service.ICheckItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 背调项目 服务实现类
 * </p>
 *
 * @author fangshuai
 * @since 2018-09-20
 */
@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements ICheckItemService {

	@Autowired
	private CheckItemMapper itemMapper;

	@Override
	public List<CheckItem> getItemListByTemplateId(Long TemplateId) {
		if(TemplateId == null ){
			return null;
		}
		
		
		return null;
	}

    @Override
    public List<CheckItemEntity> getItemListByAgencyId(Long agencyId) {
        return itemMapper.getItemListByAgencyId(agencyId);
    }

}
