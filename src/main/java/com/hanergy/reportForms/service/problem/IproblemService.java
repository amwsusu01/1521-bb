package com.hanergy.reportForms.service.problem;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.mapper.entity.Problem;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public interface IproblemService {

    public JSONObject problemlist(Problem problem);

    public  Boolean problemDetailedExcel(HttpServletResponse response, Problem problem);
}
