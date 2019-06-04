package com.hanergy.reportForms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanergy.reportForms.commons.utils.ResResult;
import com.hanergy.reportForms.mapper.RoleFunctionMapper;
import com.hanergy.reportForms.mapper.entity.RoleFunction;
import com.hanergy.reportForms.service.IRoleFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色功能 服务实现类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
@Service
public class RoleFunctionServiceImpl extends ServiceImpl<RoleFunctionMapper, RoleFunction> implements IRoleFunctionService {

    @Autowired
    RoleFunctionMapper roleFunctionMapper;

    @Override
    public JSONObject allocateFunctions(Long roleId, List<Long> functionIds) {
        // 删除原有关系
        baseMapper.delete(new QueryWrapper<RoleFunction>().eq("role",roleId));
        // 建立新关系
        List<RoleFunction> roleFunctions = functionIds.stream().map(functionId -> {
            RoleFunction function = new RoleFunction();
            function.setRole(roleId);
            function.setFunction(functionId);
            return function;
        }).collect(Collectors.toList());
        // 批量保存
        try {
            this.saveBatch(roleFunctions);
            return ResResult.success("保存成功!");
        }catch (Exception ex){
            ex.printStackTrace();
            return ResResult.failed("保存失败!");
        }
    }

    @Override
    public List<Long> querryRoleFunctionById(Long roleid) {
        return roleFunctionMapper.querryRoleFunctionById(roleid);
    }


}
