package com.hanergy.reportForms.service;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.mapper.entity.ProcessRecode;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface IprocessRecodeService {
    public JSONObject processRecodeList(ProcessRecode processRecode);

    public Boolean processRecodeExport(HttpServletResponse response,ProcessRecode processRecode);
    public List findUserOfNull();
}
