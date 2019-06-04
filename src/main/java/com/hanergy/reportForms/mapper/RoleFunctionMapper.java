package com.hanergy.reportForms.mapper;

import com.hanergy.reportForms.mapper.entity.RoleFunction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色功能 Mapper 接口
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
public interface RoleFunctionMapper extends BaseMapper<RoleFunction> {

    List<Long> querryRoleFunctionById(Long roleid);

}
