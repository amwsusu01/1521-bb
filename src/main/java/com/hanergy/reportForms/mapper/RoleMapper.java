package com.hanergy.reportForms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanergy.reportForms.mapper.entity.Role;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     *
     * @param userId 用户id,候选人id或者中介id
     * @return
     */
    List<Role> getRoleByUserId(@Param("userId") Long userId);

}
