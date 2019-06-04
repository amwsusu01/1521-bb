package com.hanergy.reportForms.service.impl;

import com.hanergy.reportForms.dto.user.RoleDto;
import com.hanergy.reportForms.mapper.RoleMapper;
import com.hanergy.reportForms.mapper.UserRoleMapper;
import com.hanergy.reportForms.mapper.entity.Role;
import com.hanergy.reportForms.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<Long> querryRoleById(Long userid) {
        return userRoleMapper.querryUserRoleById(userid);
    }

    @Override
    public List<Role> getRoleByUserId(Long userId) {
        return baseMapper.getRoleByUserId(userId);
    }
}
