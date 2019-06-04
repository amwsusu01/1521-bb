package com.hanergy.reportForms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.utils.ReturnExcel;
import com.hanergy.reportForms.mapper.problem.ProblemMapper;
import com.hanergy.reportForms.mapper.entity.Problem;
import com.hanergy.reportForms.service.problem.IproblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class ProblemServiceImpl implements IproblemService {

    @Autowired
    ProblemMapper problemMapper;

    @Override
    public JSONObject problemlist(Problem problem) {
        JSONObject jsonObject = new JSONObject();
        Integer count = problemMapper.countProblem(problem);
        jsonObject.put("count", count);
        List list = problemMapper.problemlist(problem);
        jsonObject.put("data", list);
        return jsonObject;
    }

    @Override
    public Boolean problemDetailedExcel(HttpServletResponse response, Problem problem) {
        List<Map<String, Object>> list = problemMapper.problemlist(problem);
        return ReturnExcel.problemDetailedExcel(response, list);
    }
}
