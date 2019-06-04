package com.hanergy.reportForms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanergy.reportForms.commons.ElsCommonParam;

import com.hanergy.reportForms.commons.enums.OperationConstent;
import com.hanergy.reportForms.entity.log.SystemLog;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Service
public class ElasticsearchService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    TransportClient transportClient;

    public String index(ElsCommonParam param) {
        param=convertData(param);//组装数据
        String index_id=createIndex(param);//创建索引
        return index_id;
    }
    public Map searchById(ElsCommonParam param){
        GetResponse res = transportClient.prepareGet(param.getIndexName(), null, param.getId())
                .execute()
                .actionGet();
        logger.info("index:"+res.getSource());
        return res.getSource();
    }

    public String createIndex(ElsCommonParam param){
        IndexRequest request=null;
        if (StringUtils.isBlank(param.getId())){
            request=new IndexRequest(param.getIndexName(),param.getIndexType());
        }else {
            request=new IndexRequest(param.getIndexName(),param.getIndexType(),param.getId());
        }
        ObjectMapper mapper=new ObjectMapper();
        JSONObject jsonObject=mapper.convertValue(param.getData(),JSONObject.class);
        request.source(jsonObject);
        IndexResponse response=transportClient.index(request).actionGet();
        return response.getId();
    }

    public ElsCommonParam convertData(ElsCommonParam param){
        SystemLog systemLog=SystemLog.getInstance();
        ObjectMapper mapper=new ObjectMapper();
        JSONObject jsonObject=mapper.convertValue(param.getData(),JSONObject.class);
        String ip=StringUtils.isNotBlank(jsonObject.getString("ip"))?jsonObject.getString("ip"):param.getIpAddr();
        systemLog.setLoginIpAddr(ip);
        systemLog.setSystemId(jsonObject.getString("systemId"));
        systemLog.setSystemName(jsonObject.getString("systemName"));
        systemLog.setMenuId(jsonObject.getString("menuId"));
        systemLog.setMenuName(jsonObject.getString("menuName"));
        systemLog.setUserId(jsonObject.getString("userId"));
        systemLog.setUserName(jsonObject.getString("userName"));
        systemLog.setFullName(jsonObject.getString("fullName"));
        systemLog.setProTime(new Date());
        systemLog.setProType(OperationConstent.getTypeById(jsonObject.getInteger("proType")));
        String logContent="";
        String proTarg="1521大数据分析平台";
        if (jsonObject.getInteger("proType")==OperationConstent.LOGIN.getId()){
            logContent=String.format("用户%s登陆1521大数据分析平台",jsonObject.getString("fullName"));
        }else {
            proTarg=jsonObject.getString("menuName");
            logContent=String.format("用户%s对%s的%s菜单进行了%s操作",
                    jsonObject.getString("fullName"),
                    jsonObject.getString("systemName"),
                    jsonObject.getString("menuName"),
                    OperationConstent.getTypeById(jsonObject.getInteger("proType")));
        }
        systemLog.setProTarg(proTarg);
        systemLog.setLog_content(logContent);
        param.setData(systemLog);
        return param;
    }
}
