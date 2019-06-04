package com.hanergy.reportForms.controller;


import com.alibaba.fastjson.JSONObject;

import com.hanergy.reportForms.commons.ElsCommonParam;
import com.hanergy.reportForms.commons.utils.HttpUtil;
import com.hanergy.reportForms.service.impl.ElasticsearchService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api("ElasticsearchApi")
@RestController
@RequestMapping("/elasticsearch")
public class ElsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ElasticsearchService elasticsearchService;

    /**
     *{
     "indexName": "processrecode",
     "indexType": "process",
     "id":10,
     "data": {
     "loginIpAddr": "10.0.246.121",
     "proType": "查看",
     "proTarg": "打卡明细分析",
     "fullName": "隋琛",
     "id": 10,
     "proTime": "2019-01-21 15:21:53",
     "userName": "suichen"
     }
     }
     *
     * */
    @PostMapping("/index")
    public JSONObject processRecode_Index(@RequestBody ElsCommonParam param, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            param.setIpAddr(HttpUtil.getIpAddr(request));
            String index_id=elasticsearchService.index(param);
            result.put("code", "200");
            result.put("msg", "索引成功");
            result.put("data",index_id);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "500");
            result.put("msg", "索引失败");
        }
        return result;
    }

   /* @PostMapping("/search_id")
    public JSONObject searchById(@RequestBody ElsCommonParam param) {
        JSONObject result = new JSONObject();
        try {
            Map data=elasticsearchService.searchById(param);
            result.put("code", "200");
            result.put("msg", "搜索成功");
            result.put("data",data);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "500");
            result.put("msg", "搜索失败");
        }
        return result;
    }*/
}
