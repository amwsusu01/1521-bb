package com.hanergy.reportForms.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.commons.utils.ReturnExcel;
import com.hanergy.reportForms.mapper.entity.ProcessRecode;
import com.hanergy.reportForms.mapper.ProcessRecodeMapper;
import com.hanergy.reportForms.service.IprocessRecodeService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class IprocessRecodeImpl implements IprocessRecodeService {


    @Autowired
    TransportClient client;

    @Override
    public JSONObject processRecodeList(ProcessRecode processRecode) {
        JSONObject jsonObject = findByParam(processRecode);
        return jsonObject;
    }

    @Override
    public Boolean processRecodeExport(HttpServletResponse response, ProcessRecode processRecode) {
        JSONObject json = findByParam(processRecode);
        JSONArray jsonArray=json.getJSONArray("data");
        List list=JSONObject.parseArray(jsonArray.toJSONString(),Map.class);
        return ReturnExcel.processRecodeExcel(response, list);
    }

    public JSONObject findByParam(ProcessRecode processRecode) {
        BoolQueryBuilder boolQueryBuilder =QueryBuilders.boolQuery();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("proTime");
        if (StringUtils.isNotBlank(processRecode.getUserFullName())) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery("fullName", processRecode.getUserFullName()));//模糊搜索
        }
        if (StringUtils.isNotBlank(processRecode.getStart_date())) {
            Date start = DateUtils.stringToDate2(processRecode.getStart_date(), DateUtils.PATTERN_YYYYMMDDHHMMSS);
            rangeQueryBuilder.from(start.getTime());
        }
        if (StringUtils.isNotBlank(processRecode.getEnd_date())) {
            Date end = DateUtils.stringToDate2(processRecode.getEnd_date(), DateUtils.PATTERN_YYYYMMDDHHMMSS);
            rangeQueryBuilder.to(end.getTime());
        }
        boolQueryBuilder.must(rangeQueryBuilder);
        SearchRequestBuilder resquestBuilder = client.prepareSearch("1521_operation_log")
                .setTypes("processrecode").setQuery(boolQueryBuilder);
        SearchResponse myresponse = resquestBuilder.execute().actionGet();
        SearchHits hits = myresponse.getHits();
        Long count = hits.getTotalHits();
        if (processRecode.getPageNo() != null && processRecode.getPageSize() != null) {
            Integer start = (processRecode.getPageNo() - 1) * processRecode.getPageSize();
            Integer end = processRecode.getPageSize();
            resquestBuilder.setFrom(start).setSize(end);
        } else {
            resquestBuilder.setFrom(0).setSize(Integer.valueOf(count.toString()));
        }
        resquestBuilder.addSort("proTime",SortOrder.DESC);
        myresponse = resquestBuilder.execute().actionGet();
        hits = myresponse.getHits();

        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map map = hit.getSource();
            Long timeStamp = (Long) (map.get("proTime"));
            if (timeStamp != null) {
                Date date = new Date(timeStamp);
                String protime = DateUtils.dateToString(date, DateUtils.PATTERN_YYYYMMDDHHMMSS);
                map.put("proTime", protime);
            }
            list.add(map);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count",count);
        jsonObject.put("data", list);
        return jsonObject;
    }

    public List findUserOfNull(){
        List<Map<String,String>> idList=new ArrayList<>();
        SearchRequestBuilder resquestBuilder = client.prepareSearch("1521_operation_log")
                .setTypes("processrecode").setQuery(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery("fullName")));
        SearchResponse myresponse = resquestBuilder.execute().actionGet();
        SearchHits hits = myresponse.getHits();
        Long count = hits.getTotalHits();
        resquestBuilder.setFrom(0).setSize(Integer.valueOf(count.toString()));
        myresponse=resquestBuilder.execute().actionGet();
        hits=myresponse.getHits();
        for (SearchHit hit : hits) {
            Map data=hit.getSource();
            data.put("index_id",hit.getId());
            idList.add(data);
        }

        for (Map<String,String> data:idList){
            DeleteRequest request=new DeleteRequest("1521_operation_log","processrecode",data.get("index_id"));
            client.delete(request).actionGet().getId();
        }
        return idList;
    }
}
