package com.hanergy.reportForms.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hanergy.reportForms.VO.PasswordVo;
import com.hanergy.reportForms.VO.UserVo;
import com.hanergy.reportForms.commons.enums.EnumRoleType;
import com.hanergy.reportForms.commons.enums.EnumVerifySource;
import com.hanergy.reportForms.commons.utils.*;
import com.hanergy.reportForms.commons.utils.secret.RSAUtils;
import com.hanergy.reportForms.dto.user.UserDto;
import com.hanergy.reportForms.mapper.UserMapper;
import com.hanergy.reportForms.mapper.entity.*;
import com.hanergy.reportForms.service.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.hanergy.reportForms.commons.utils.cont.DefaultConst.REDIS_LOGINKEY;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author duronghong
 * @since 2018-09-12
 */
@Service
//@DS("first")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    public static Logger logger = LogManager.getLogger();
    @Autowired
    private IAgencyService agencyService;
    @Autowired
    private IBeSelectedService beSelectedService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRedisService redisService;


    @Override
    public IPage<User> queryPage(Wrapper<User> userWrapper, Integer pageNo, Integer pageSize) {
        Page<User> page = new Page<User>(pageNo, pageSize);
        IPage<User> iPage = baseMapper.selectPage(page, userWrapper);

        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            for (User user : iPage.getRecords()) {
                user.setRoles(roleService.getRoleByUserId(user.getId()));
            }
        }
        return iPage;
    }

    @Override
    public JSONObject login(UserVo userVo, HttpServletResponse response) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("job_number", userVo.getName());
        User user = this.getOne(userQueryWrapper);
        if (user != null) { // 查找用户表,查看该用户是不是公司员工
            if (Md5SaltTool.MD5(userVo.getPassword()).equals(user.getPassword())) {
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setName(user.getName());
                userDto.setUserName(user.getJobNumber());
                userDto.setJobNumber(user.getJobNumber());
                userDto.setMail(user.getMail());
                userDto.setUserType(EnumVerifySource.SF.getCode());
                userDto.setStatus(user.getCancel());
                List<Role> dtos = roleService.getRoleByUserId(user.getId());
//                if(CollectionUtils.isEmpty(dtos)){
//                    return ResResult.failed("登陆失败!");
//                }
                userDto.setRoles(dtos);
//                writeToCookie(userDto, response);
                logger.info("登陆成功     返回用户信息：" + JSONObject.toJSONString(userDto));
                String uriComponent = LZString.compressToEncodedURIComponent(userDto.toString());
                JSONObject jsonObject = ResResult.success(uriComponent, "登陆成功!");
                jsonObject.put("userId", userDto.getId());
                return jsonObject;
            }
            return ResResult.failed("密码错误!");
        } else {
            // 查看中介公司表,查看登陆用户是否是中介用户
            QueryWrapper<Agency> agencyQueryWrapper = new QueryWrapper<>();
            agencyQueryWrapper.eq("login_name", userVo.getName());
            Agency agency = agencyService.getOne(agencyQueryWrapper);
            if (agency != null) {
                if (Md5SaltTool.MD5(userVo.getPassword()).equals(agency.getPassword())) {
                    UserDto userDto = new UserDto();
                    userDto.setId(agency.getId());
                    userDto.setName(agency.getName());
                    userDto.setMail(agency.getMail());
                    userDto.setUserName(agency.getLoginName());
                    userDto.setUserType(EnumVerifySource.AGENCY.getCode());
                    userDto.setStatus(agency.getCancel());
                    List<Role> roleDtos = roleService.getRoleByUserId(agency.getId());
//                    if(CollectionUtils.isEmpty(roleDtos)){
//                        return ResResult.failed("登陆失败!");
//                    }
                    if (CollectionUtils.isEmpty(roleDtos)) {
                        roleDtos = new ArrayList<>();
                        Role role = new Role();
                        role.setId(EnumRoleType.AGENCY.getCode());
                        role.setName(EnumRoleType.AGENCY.getName());
                        role.setCancel(0);
                        role.setFunctions(new ArrayList<>());
                        roleDtos.add(role);
                    }
                    userDto.setRoles(roleDtos);
//                    writeToCookie(userDto, response);
                    logger.info("登陆成功     返回用户信息：" + JSONObject.toJSONString(userDto));
                    String uriComponent = LZString.compressToEncodedURIComponent(userDto.toString());
                    JSONObject jsonObject = ResResult.success(uriComponent, "登陆成功!");
                    jsonObject.put("userId", userDto.getId());
                    return jsonObject;
                }
                return ResResult.failed("密码错误!");
            } else {
                // 查找候选人表,查看是否是候选人登陆
                QueryWrapper<BeSelected> beSelectedQueryWrapper = new QueryWrapper<>();
                beSelectedQueryWrapper.eq("mobilePhone", userVo.getName());
                List<BeSelected> beSelecteds = beSelectedService.list(beSelectedQueryWrapper);
                if (CollectionUtils.isNotEmpty(beSelecteds)) {
                    for (BeSelected beSelected : beSelecteds) {
                        if (Md5SaltTool.MD5(userVo.getPassword()).equals(beSelected.getPassword())) {
                            UserDto userDto = new UserDto();
                            userDto.setId(beSelected.getId());
                            userDto.setName(beSelected.getName());
                            userDto.setMail(beSelected.getMail());
                            userDto.setUserName(beSelected.getMobilephone());
                            userDto.setUserType(EnumVerifySource.CANDIDATE.getCode());
                            userDto.setMobilePhone(beSelected.getMobilephone());
                            userDto.setStatus(beSelected.getCancel());
                            List<Role> roleDtos = roleService.getRoleByUserId(beSelected.getId());
//                        if(CollectionUtils.isEmpty(roleDtos)){
//                            return ResResult.failed("登陆失败!");
//                        }
                            if (CollectionUtils.isEmpty(roleDtos)) {
                                roleDtos = new ArrayList<>();
                                Role role = new Role();
                                role.setId(EnumRoleType.CADIDATE.getCode());
                                role.setName(EnumRoleType.CADIDATE.getName());
                                role.setCancel(0);
                                role.setFunctions(new ArrayList<>());
                                roleDtos.add(role);
                            }
                            userDto.setRoles(roleDtos);
//                        writeToCookie(userDto, response);
                            logger.info("登陆成功     返回用户信息：" + JSONObject.toJSONString(userDto));
                            String uriComponent = LZString.compressToEncodedURIComponent(userDto.toString());
                            JSONObject jsonObject = ResResult.success(uriComponent, "登陆成功!");
                            jsonObject.put("userId", userDto.getId());
                            return jsonObject;
                        }
//                        return ResResult.failed("密码错误!");
                    }
                }


                // 查找一遍,查不到,则用户信息不存在
                return ResResult.failed("登陆失败");
            }
        }
    }

    @Override
    public JSONObject updatePassword(PasswordVo passwordVo) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("job_number", passwordVo.getUserName());
        User user = this.getOne(userQueryWrapper);
        if (user != null) {
            if (StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(Md5SaltTool.MD5(passwordVo.getNewPassword()));
                user.updateById();
                return ResResult.success("密码设置成功!");
            } else if (user.getPassword().equals(Md5SaltTool.MD5(passwordVo.getOldPassword()))) {
                user.setPassword(Md5SaltTool.MD5(passwordVo.getNewPassword()));
                user.updateById();
                return ResResult.success("密码修改成功!");
            }
            return ResResult.failed("旧密码不正确!");
        } else {
            QueryWrapper<Agency> agencyQueryWrapper = new QueryWrapper<>();
            agencyQueryWrapper.eq("login_name", passwordVo.getUserName());
            Agency agency = agencyService.getOne(agencyQueryWrapper);
            if (agency != null) {
                if (StringUtils.isEmpty(agency.getPassword())) {
                    agency.setPassword(Md5SaltTool.MD5(passwordVo.getNewPassword()));
                    agency.updateById();
                    return ResResult.success("密码设置成功!");
                } else if (agency.getPassword().equals(Md5SaltTool.MD5(passwordVo.getOldPassword()))) {
                    agency.setPassword(Md5SaltTool.MD5(passwordVo.getNewPassword()));
                    agency.updateById();
                    return ResResult.success("密码修改成功!");
                }
                return ResResult.failed("旧密码不正确");
            } else {
                QueryWrapper<BeSelected> beSelectedQueryWrapper = new QueryWrapper<>();
                beSelectedQueryWrapper.eq("mobilePhone", passwordVo.getUserName());
                BeSelected beSelected = beSelectedService.getOne(beSelectedQueryWrapper);
                if (beSelected != null) {
                    if (StringUtils.isEmpty(beSelected.getPassword())) {
                        beSelected.setPassword(Md5SaltTool.MD5(passwordVo.getNewPassword()));
                        beSelected.updateById();
                        return ResResult.success("密码设置成功!");
                    } else if (beSelected.getPassword().equals(Md5SaltTool.MD5(passwordVo.getOldPassword()))) {
                        beSelected.setPassword(Md5SaltTool.MD5(passwordVo.getNewPassword()));
                        beSelected.updateById();
                        return ResResult.success("密码修改成功!");
                    }
                    return ResResult.failed("旧密码不正确");
                }
                return ResResult.failed("用户信息不存在!");
            }

        }
    }

    @Override
    public JSONObject validLogin(Long loginUserId, HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return ResResult.failed("登陆信息失败!");
        }
        String loginKey = REDIS_LOGINKEY + loginUserId;
        String get = redisService.stringGet(loginKey);
        if (StringUtils.isEmpty(get)) {
            return ResResult.failed("登陆已失效,请重新登陆!");
        }
        return ResResult.success(JSONArray.parseArray(get), "请求成功!");
    }

    @Override
    public UserDto verifyLogin(HttpServletRequest request, String cookieName) {

        if (StrUtil.isEmpty(cookieName)) {
            return null;
        }
        Cookie cookie = CookiesOperation.getCookieByName(request, cookieName);
        if (cookie == null) {
            return null;
        }
        String token = null;
        try {
            token = RSAUtils.decryptByPrivateKey(cookie.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        String userId = token.substring(8, token.length() - 13);
        UserDto user = new UserDto();
        String redisKey = REDIS_LOGINKEY + userId;


        logger.info("   userId  :" + userId + ",  redisKey:" + redisKey);
        String result = redisService.stringGet(redisKey);
        if (StrUtil.isEmpty(result)) {
            return null;
        }
        user = JSONObject.parseObject(result, UserDto.class);
        logger.info(" user info : " + JSONObject.toJSONString(user));
        //刷新缓存
        redisService.expire(redisKey, 2L,
                JSONObject.toJSONString(user), TimeUnit.HOURS);
        return user;
    }

    @Override
    public JSONObject loginOut(Long userId) {
        String redisKey = REDIS_LOGINKEY + userId;
        String result = redisService.stringGet(redisKey);
        if (StringUtils.isNotEmpty(result)) {
            redisService.remove(redisKey);
            return ResResult.success("退出成功!");
        }
        return ResResult.failed("退出失败!");
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = getById(userId);
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUserName(user.getJobNumber());
        userDto.setJobNumber(user.getJobNumber());
        userDto.setMail(user.getMail());
        // 按需添加
//        userDto.setUserType(EnumVerifySource.SF.getCode());
        List<Role> dtos = roleService.getRoleByUserId(user.getId());

        List<Role> roleHttp = getRoleHttp(user.getJobNumber());
        if (roleHttp != null && roleHttp.size() > 0) {
            dtos.addAll(roleHttp);
        }
        userDto.setRoles(dtos);

        System.out.println(userDto.toString());
        return userDto;
    }


    private String formatRedisKey(String userId, String ip) {
        return REDIS_LOGINKEY + userId + ip;
    }

    private List<Role> getRoleHttp(String jobNumber) {
        try {
            String doGet = HttpTookit.doGet("http://192.168.18.204/admin/api/v1/user/roles?job=" + jobNumber, null);
            if (StringUtils.isNotEmpty(doGet)) {
                JSONObject result = JSONObject.parseObject(doGet);
                Integer status = result.getInteger("status");
                if (status == 1) {
                    return new ArrayList<Role>();
                }
                JSONArray data = result.getJSONArray("data");
                if (data == null || data.size() <= 0) {
                    return new ArrayList<>();
                }
                return resolveJSONObject(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.getExceptionLogger().error("[USERSERVICE===>>getAdminHttp]出现异常", e.getMessage());
        }
        return Lists.newArrayList();
    }

    private List<Role> resolveJSONObject(JSONArray jsonArray) {
        List<Role> list = new ArrayList<>();
        if (jsonArray != null && jsonArray.size() > 0) {

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Role role = new Role();
                role.setId(jsonObject.getLong("roleId"));
                role.setName(jsonObject.getString("roleName"));
                role.setRemark(jsonObject.getString("remark"));
                role.setCancel(0);
                JSONArray menus = jsonObject.getJSONArray("menus");
                List<Function> functions = new ArrayList<>();
                if (menus != null && menus.size() > 0) {
                    for (int j = 0; j < menus.size(); j++) {
                        JSONObject menu = menus.getJSONObject(j);
                        Function function = new Function();
                        function.setId(menu.getLong("menuId"));
                        function.setParentId(menu.getLong("parentId"));
                        function.setName(menu.getString("name"));
                        function.setUrl(menu.getString("url"));
                        function.setPerms(menu.getString("perms"));
                        function.setOrderNum(menu.getInteger("orderNum"));
                        function.setIcon(menu.getString("icon"));
                        function.setType(menu.getInteger("type"));
                        functions.add(function);
                    }
                }
                role.setFunctions(functions);
                list.add(role);
            }
        }
        return list;
    }
}
