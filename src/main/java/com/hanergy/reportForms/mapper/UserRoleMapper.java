package com.hanergy.reportForms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanergy.reportForms.mapper.entity.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Long> querryUserRoleById(Long userid);

    Long saveUserRole(UserRole userRole);
}
