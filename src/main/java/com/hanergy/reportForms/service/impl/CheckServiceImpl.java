package com.hanergy.reportForms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanergy.reportForms.commons.enums.EnumBeSelectedStatus;
import com.hanergy.reportForms.mapper.CheckMapper;
import com.hanergy.reportForms.mapper.entity.BeSelected;
import com.hanergy.reportForms.mapper.entity.Check;
import com.hanergy.reportForms.service.IBeSelectedService;
import com.hanergy.reportForms.service.ICheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 背调信息 服务实现类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements ICheckService {

    @Autowired
    private IBeSelectedService beSelectedService;

    @Override
    public Boolean checkReportEnd(Long beSelected) {
        BeSelected selected = beSelectedService.getById(beSelected);

        QueryWrapper<Check> wrapper = new QueryWrapper<>();
        wrapper.eq("beSelected", beSelected);
        List<Check> checks = this.list(wrapper);
        boolean result = false;
        if (CollectionUtils.isNotEmpty(checks)) {
            for (Check check : checks) {
                if (1 == (check.getVerifyStatus())) {
                    result = true;
                }
            }

        }
        if (result) {
            selected.setCheckStatus(EnumBeSelectedStatus.CHECK_OVER.getCode());
            selected.setCheckStatusTime(new Date());
            selected.updateById();
            Integer self = beSelectedService.verifyCheckIsOnlySelf(beSelected);
            if (1 == self) { // 只有自主背调
                selected.setStaffStatus(0);
                selected.setStaffStatusDate(new Date());
            } else if (2 == self) { // 只有委托背调
                selected.setStaffStatus3(0);
                selected.setStaffStatusDate3(new Date());
            } else if (3 == self) {
                selected.setStaffStatus3(0);
                selected.setStaffStatusDate3(new Date());
            }
            selected.updateById();
            return true;
        }
        return false;
    }
}
