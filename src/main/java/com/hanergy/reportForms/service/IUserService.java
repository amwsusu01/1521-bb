package com.hanergy.reportForms.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanergy.reportForms.VO.PasswordVo;
import com.hanergy.reportForms.VO.UserVo;
import com.hanergy.reportForms.dto.user.UserDto;
import com.hanergy.reportForms.mapper.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
public interface IUserService extends IService<User> {


    IPage<User> queryPage(Wrapper<User> userWrapper, Integer page, Integer pageSize);

    JSONObject login(UserVo userVo, HttpServletResponse response);

    JSONObject updatePassword(PasswordVo passwordVo);

    JSONObject validLogin(Long loginUserId, HttpServletRequest request);
    /**
     * cookieName
     * @param cookieName
     * @return
     */
    UserDto verifyLogin(HttpServletRequest request ,String cookieName);

    JSONObject loginOut(Long userId);

    UserDto getUserById(Long userId);
}
