package com.hanergy.reportForms.commons.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hanergy.reportForms.commons.ElsCommonParam;
import com.hanergy.reportForms.commons.enums.OperationConstent;
import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.commons.utils.HttpUtil;
import com.hanergy.reportForms.entity.log.SystemLog;
import com.hanergy.reportForms.service.impl.ElasticsearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Aspect
@Component
public class WebControllerAop {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    ElasticsearchService elasticsearchService;

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.hanergy.reportForms.controller.*.*(..))" +
            "&&!execution(public * com.hanergy.reportForms.controller.ElsController.*(..))" +
            "&&!execution(public * com.hanergy.reportForms.controller.ReturnController.getDate(..))" +
            "&&!execution(public * com.hanergy.reportForms.controller.ProductionSupplyMarketingController.cglmxDetail(..))" +
            "&&!execution(public * com.hanergy.reportForms.controller.DetailCollectController.getAllOrganization(..))" +
            "&&!execution(public * com.hanergy.reportForms.controller.PersonnelFlowController.scheduled(..))" +
            "&&!execution(public * com.hanergy.reportForms.controller.UserPermissionController.selectDeptList(..))" +
            "&&!execution(public * com.hanergy.reportForms.controller.ProductController.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知：在连接点之前执行的通知
     */
   /* @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        Object[] args = joinPoint.getArgs();
        String params = "";
        if (RequestMethod.POST.name().equals(method)) {
            if (args.length > 0) {
                Object object = args[0];
                params = JSON.toJSONString(object);
            }
        } else if (RequestMethod.GET.name().equals(method)) {
            params = queryString;
        }
        logger.info("请求开始===地址:" + url);
        logger.info("请求开始===ip:" + ip);
        logger.info("请求开始===类型:" + method);
        logger.info("请求开始===参数:" + params);
    }*/

    /**
     * 控制层执行完后记录用户操作
     */
    @AfterReturning(value = "webLog()", returning = "result")
    public void doAfter(JoinPoint point, Object result) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String ip = HttpUtil.getIpAddr(request);
        String method = request.getMethod();
        Object[] args = point.getArgs();
        String params = "";
        JSONObject jsonObject = null;
        if (args.length > 0) {
            Object object = args[0];
            if (object instanceof ArrayList) {
                params = JSON.toJSONString(object);
            } else {
                if (RequestMethod.POST.name().equals(method)) {
                    params = JSON.toJSONString(object);
                    jsonObject = JSONObject.parseObject(params);
                } else if (RequestMethod.GET.name().equals(method)) {
                    Map<String, String[]> paremeterMap = request.getParameterMap();
                    Iterator<Map.Entry<String, String[]>> iterator = paremeterMap.entrySet().iterator();
                    Map<String, String> map = new HashMap<>();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String[]> entry = iterator.next();
                        map.put(entry.getKey(), entry.getValue()[0]);
                    }
                    params = JSON.toJSONString(map);
                    jsonObject = JSONObject.parseObject(params);
                }
            }
            if (jsonObject != null) {
                if (StringUtils.isBlank(jsonObject.getString("systemId")) ||
                        StringUtils.isBlank(jsonObject.getString("systemName")) ||
                        StringUtils.isBlank(jsonObject.getString("menuId")) ||
                        StringUtils.isBlank(jsonObject.getString("menuName")) ||
                        StringUtils.isBlank(jsonObject.getString("userId")) ||
                        StringUtils.isBlank(jsonObject.getString("userName")) ||
                        StringUtils.isBlank(jsonObject.getString("fullName")) ||
                        StringUtils.isBlank(jsonObject.getString("proType"))) {
                    return;
                }
                if (jsonObject.getInteger("proType") == OperationConstent.VIEW.getId()
                        && jsonObject.getBoolean("isNo") != null
                        && jsonObject.getBoolean("isNo").equals(true)) {//判断点击页码的时候不生成操作记录
                    return;
                }

                jsonObject.put("ip", ip);
                String index_id = index(jsonObject);
                logger.info("index_id:" + index_id);
            }
        }
        String methodName = point.getSignature().getName();
        logger.info("请求开始===地址:" + url);
        logger.info("请求开始===ip:" + ip);
        logger.info("请求开始===方法类型:" + method);
        logger.info("请求开始===参数:" + params);
        logger.info("调用后连接点方法为：" + methodName + ",目标方法执行结果为：" + result);
    }

    /**
     * 创建索引
     */
    public String index(JSONObject jsonObject) {
        ElsCommonParam param = new ElsCommonParam();
        param.setIndexName("1521_operation_log");
        param.setIndexType("processrecode");
        param.setData(jsonObject);
        String index_id = elasticsearchService.index(param);
        return index_id;
    }
}
