package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hanergy.reportForms.commons.BaseController;
import com.hanergy.reportForms.commons.enums.EnumVerifySource;
import com.hanergy.reportForms.commons.utils.JsonUtil;
import com.hanergy.reportForms.commons.utils.LZString;
import com.hanergy.reportForms.commons.utils.LogUtils;
import com.hanergy.reportForms.commons.utils.ResResult;
import com.hanergy.reportForms.commons.utils.SsoUtil;
import com.hanergy.reportForms.commons.utils.secret.RSAUtils;
import com.hanergy.reportForms.dto.user.UserDto;
import com.hanergy.reportForms.service.IRedisService;
import com.hanergy.reportForms.service.IRoleService;
import com.hanergy.reportForms.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hanergy.reportForms.commons.utils.cont.DefaultConst.LOGIN_COOKIE;
import static com.hanergy.reportForms.commons.utils.cont.DefaultConst.REDIS_LOGINKEY;

/**
 * @ClassName RedirectLoginRestController
 * @Description TODO 通过OA登陆本系统
 * @Author DURONGHONG
 * @DATE 2018/10/16 14:27
 * @Version 1.0
 **/
@Api("OA跳转")
@Controller
@RequestMapping(value = "/auth")
public class RedirectLoginRestController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IRoleService roleService;

    private static String REDIS_USER_TOKEN = "REDIS_USER_TOKEN";

    @Value("${project.background-system.loginUrl}")
    private String projectDomain;

//    @RequestMapping(value = "/getEncrptCode",method = RequestMethod.POST)
//    @ResponseBody
//    public JSONObject getEncrpytCode( @RequestBody OALoginDto oaLoginDto) throws Exception {
//        JSONObject result = new JSONObject();
//        if (oaLoginDto.isEmpty()) {
//            result.put("status", 0);
//            result.put("msg", "参数不可为空!");
//            result.put("content", "");
//            result.put("code", "01");
//            return result;
//        }
//        if (!oaLoginDto.getCorpCode().equals("Hanergy") || !oaLoginDto.getEncryptKey().equals("hanergydayee123")) {
//            result.put("status", 0);
//            result.put("msg", "验证失败!");
//            result.put("content", "");
//            result.put("code", "01");
//            return result;
//        }
//        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("job_number", oaLoginDto.getEncryptInfo().getJobNumber());
//        SysUserEntity sysUserEntity = sysUserService.getOne(wrapper);
//        if (sysUserEntity == null) {
//            // 用户不存在
//            result.put("status", 0);
//            result.put("msg", "用户不存在!");
//            result.put("content", "");
//            result.put("code", "01");
//            return result;
//        } else {
//            if (sysUserEntity.getStatus().equals(0)) {
//                result.put("status", 0);
//                result.put("msg", "用户被禁用!");
//                result.put("content", "");
//                result.put("code", "01");
//                return result;
//            }
//            result.put("status", 0);
//            result.put("msg", "请求成功!");
//            EncrptyInfo encrptyInfo = oaLoginDto.getEncryptInfo();
//            encrptyInfo.setUserId(sysUserEntity.getUserId());
//            String content = RSAUtils.encryptByPublicKey(JSONObject.toJSONString(encrptyInfo));
//            // 采用Lzstring加密的方式主要是解决 非对称加密之后在url传递过程中的"+"及其他符号丢失的问题
//            String s = LZString.compressToEncodedURIComponent(content);
//            result.put("content", s);
//            result.put("code", "00");
//            return result;
//        }
//    }

    @ApiOperation(value = "OA用户跳转到HRA")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void redirectHomePage(
            @RequestParam(value = "corpCode", defaultValue = "") String corpCode,
            @RequestParam(value = "ticket", defaultValue = "") String ticket,
            HttpServletResponse response
    ) throws Exception {
        LogUtils.getBussinessLogger().info("OA系统调用HRA....");
        LogUtils.getBussinessLogger().info("ticket===>>>>" + ticket);
        if (StringUtils.isBlank(ticket) || StringUtils.isBlank(corpCode) || !"Hanergy".equals(corpCode)) {
            JSONObject result = new JSONObject();
            result.put("status", 1);
            result.put("msg", "参数有误!");
            result.put("code", "01");
            response.getWriter().write(result.toJSONString());
            String url = "/index.html?"+ticket;
            response.sendRedirect(url);
        }else{
            String s1 = LZString.decompressFromEncodedURIComponent(ticket);
            String key = RSAUtils.decryptByPrivateKey(s1);

            JSONObject parseObject = JSONArray.parseObject(key);
            Long userId = parseObject.getLong("userId");
            //用户工号
            String jobNumber = parseObject.getString("jobNumber");

            JSONObject result = new JSONObject();
            result.put("userId", userId);
            result.put("jobNumber", jobNumber);
            String token = getToken(userId);
            result.put("token", token);
            String url = "/index.html?"+ticket;
            response.sendRedirect(url);
        }
    }

    @ResponseBody
    @ApiOperation(value = "登陆测评系统")
    @RequestMapping(value = "/loginTocPing", method = RequestMethod.GET)
    public String loginTocPing(
            @RequestParam(value = "corpCode", defaultValue = "") String corpCode,
            @RequestParam(value = "ticket", defaultValue = "") String ticket,
            HttpServletResponse response
    ) throws Exception {
        if (StringUtils.isEmpty(ticket)) {
            JSONObject result = new JSONObject();
            result.put("status", 1);
            result.put("msg", "参数不可为空!");
            result.put("code", "01");
            response.getWriter().write(result.toJSONString());
        }
        String s1 = LZString.decompressFromEncodedURIComponent(ticket);
        String key = RSAUtils.decryptByPrivateKey(s1);
        JSONObject parseObject = JSONArray.parseObject(key);
        //员工编号
        String jobNumber = parseObject.getString("jobNumber");

        Map<String, String> map = Maps.newHashMap();
        map.put("userId", jobNumber);
        map.put("timestamp", "" + System.currentTimeMillis());

        String secret = "SqporYHFnZLE4ySfjlYJKw==";
        final String encrypted = SsoUtil.encrypt(JSON.toJSONString(map), secret);
        return  encrypted;
    }

    @ApiOperation(value = "OA系统登陆后获取用户信息")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public void getUserInfo(
            @RequestParam(value = "token", defaultValue = "") String token,
            HttpServletResponse response
    ) {


        if (StringUtils.isEmpty(token)) {
            try {
                JsonUtil.printToJson(null, response, ResResult.failed("验证失败!"));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String decrptString = null;
        try {
            decrptString = decrptString(token);
        } catch (Exception e) {
            // 非法token
            LogUtils.getExceptionLogger().error(e.getMessage(), e);
            try {
                JsonUtil.printToJson(null, response, ResResult.failed("请求失败!"));
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        JSONObject jsonObject = JSONObject.parseObject(decrptString);
        Long userId = jsonObject.getLong("userId");
        String encrptToken = jsonObject.getString("token");

        String redisKey = REDIS_USER_TOKEN + "_" + userId + "_" + encrptToken;
        if (!redisService.hasKey(redisKey)) {
            try {
                JsonUtil.printToJson(null, response, ResResult.failed("登陆信息已失效,请重新登陆!"));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String get = redisService.stringGet(redisKey);
        if (StringUtils.isNotEmpty(get)) {
            UserDto userDto = userService.getUserById(userId);
            if (userDto != null) {
                userDto.setUserType(EnumVerifySource.SF.getCode());
                String uriComponent = LZString.compressToEncodedURIComponent(JSONObject.toJSONString(userDto));
                JSONObject success = ResResult.success(uriComponent, "登陆成功!");
                writeToCookie(userDto, response, success);
                return;
            } else {
                try {
                    JsonUtil.printToJson(null, response, ResResult.failed("信息获取失败!"));
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                JsonUtil.printToJson(null, response, ResResult.failed("登陆验证失败!"));
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 写入cookie
    private void writeToCookie(UserDto userDto, HttpServletResponse response, JSONObject jsonObject) {
        try {
            String token = getToken(userDto.getId());
            String loginKey = REDIS_LOGINKEY + userDto.getId();
            JsonUtil.printCookieObject(response, jsonObject, LOGIN_COOKIE, token, -1);
            redisService.expire(loginKey, 7200L, JSONObject.toJSONString(userDto), TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String encrptString(String str) throws Exception {
        String rsaString = RSAUtils.encryptByPublicKey(str);
        return LZString.compressToEncodedURIComponent(rsaString);
    }

    private static String decrptString(String str) throws Exception {
        String uriComponent = LZString.decompressFromEncodedURIComponent(str);
        return RSAUtils.decryptByPrivateKey(uriComponent);
    }

    /*public static void main(String[] args) throws Exception {
        JSONObject result = new JSONObject();
        result.put("userId", 5671312312392314L);
        String token = getToken(5671312312392314L);
        result.put("token", token);
        System.out.println("token===>>" + token);
        String redisKey = REDIS_USER_TOKEN + "_" + 5671312312392314L + "_" + token;

        System.out.println("redisKey===>>>" + redisKey);
        System.out.println("time====>>>" + System.currentTimeMillis());
        String encrptToken = encrptString(result.toJSONString());
        String url = "http://192.168.18.204/background/auth/getUserInfo?token=" + encrptToken;
        System.out.println(url);
    }*/
}
