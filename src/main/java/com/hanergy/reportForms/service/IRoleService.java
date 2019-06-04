package com.hanergy.reportForms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanergy.reportForms.mapper.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
public interface IRoleService extends IService<Role> {

    List<Long> querryRoleById(Long userid);

    List<Role> getRoleByUserId(Long userId);

}
