package com.hanergy.reportForms.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanergy.reportForms.mapper.entity.RoleFunction;

import java.util.List;

/**
 * <p>
 * 角色功能 服务类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
public interface IRoleFunctionService extends IService<RoleFunction> {

    JSONObject allocateFunctions(Long roleId, List<Long> list);

    List<Long> querryRoleFunctionById(Long roleid);
}
