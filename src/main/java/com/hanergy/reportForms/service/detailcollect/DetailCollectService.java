package com.hanergy.reportForms.service.detailcollect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanergy.reportForms.commons.CommonSearchParam;
import com.hanergy.reportForms.commons.utils.ReturnExcel;
import com.hanergy.reportForms.mapper.detailcollect.DetailCollectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DetailCollectService {

    @Autowired
    DetailCollectMapper detailCollectMapper;

    /*超过4次(含)未请假未提报明细表*/
    public JSONObject cgscdetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.cgscdetail(param);
        Integer count = detailCollectMapper.countCgscdetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    /*提报月平均条数小于5明细表*/
    public JSONObject tsxywDetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.tsxywDetail(param);
        Integer count = detailCollectMapper.countTsxywDetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    /*提报月平均字数小于5明细表*/
    public JSONObject zsxywDetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.zsxywDetail(param);
        Integer count = detailCollectMapper.countZsxywDetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }
    /*9点之前提报数据明细表*/
    public JSONObject jdzqDetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.jdzqDetail(param);
        Integer count = detailCollectMapper.countJdzqDetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    /*12点之前提报数据明细表*/
    public JSONObject sedzqDetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.sedzqDetail(param);
        Integer count = detailCollectMapper.countSedzqDetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    /*提报内容重复超6次(含)汇总表*/
    public JSONObject cglhzDetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.cglhzDetail(param);
        Integer count = detailCollectMapper.countCglhzDetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    /*提报内容重复超6次（含）明细表*/
    public JSONObject cglmxDetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.cglmxDetail(param);
        Integer count = detailCollectMapper.countCglmxDetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    /*部门问题明细表*/
    public JSONObject issueDetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.issueDetail(param);
        Integer count = detailCollectMapper.countIssueDetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    /*部门反省明细表*/
    public JSONObject introspectionDetail(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.introspectionDetail(param);
        Integer count = detailCollectMapper.countIntrospectionDetail(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }

    /**
     * 获取所有控股集团以及集团下的所有事业群，事业部，省公司，以及部门
     */
    public JSONObject getAllOrganization(String konggujituan,String shiyequn,String shiyebu,String shenggongsi){
        JSONObject jsonObject = new JSONObject();
        List<Map<String,String>>list=detailCollectMapper.getAllOrganization(konggujituan,shiyequn,shiyebu,shenggongsi);
        List<Object> jituanList = new ArrayList<>();
        List<Object> shiyequnList = new ArrayList<>();
        List<Object> shiyebuList = new ArrayList<>();
        List<Object> shenggongsiList = new ArrayList<>();
        List<Object> deptList = new ArrayList<>();
        for (Map<String,String> data : list) {
            if (StringUtils.isNotBlank(data.get("konggujituan")) && !jituanList.contains(data.get("konggujituan"))) {
                jituanList.add(data.get("konggujituan"));
            }
            if (StringUtils.isNotBlank(data.get("shiyequn"))&& !shiyequnList.contains(data.get("shiyequn"))) {
                shiyequnList.add(data.get("shiyequn"));
            }
            if (StringUtils.isNotBlank(data.get("shiyebu"))&& !shiyebuList.contains(data.get("shiyebu"))) {
                shiyebuList.add(data.get("shiyebu"));
            }
            if (StringUtils.isNotBlank(data.get("shenggongsi"))&& !shenggongsiList.contains(data.get("shenggongsi"))) {
                shenggongsiList.add(data.get("shenggongsi"));
            }
            if (StringUtils.isNotBlank(data.get("dept_name")) && !deptList.contains(data.get("dept_name"))) {
                deptList.add(data.get("dept_name"));
            }
        }
        jsonObject.put("jituanList", jituanList);
        jsonObject.put("shiyequnList", shiyequnList);
        jsonObject.put("shiyebuList", shiyebuList);
        jsonObject.put("shenggongsiList", shenggongsiList);
        jsonObject.put("deptList", deptList);
        return jsonObject;
    }

    /*全员提报数据明细*/
    public JSONObject allReport(CommonSearchParam param) {
        JSONObject jsonObject = new JSONObject();
        List<Map> list = detailCollectMapper.allReport(param);
        Integer count = detailCollectMapper.countAllReport(param);
        jsonObject.put("data", list);
        jsonObject.put("count", count);
        return jsonObject;
    }
    public void export(CommonSearchParam searchParam,HttpServletResponse response){
        List list=SwitchTable(searchParam);
        if (searchParam.getType()!=10){
            ReturnExcel.printFormsExcel(response,list,searchParam.getTabType());
        }else {
            ReturnExcel.allReportExport(response,list);
        }
    }

    public List<Map> SwitchTable(CommonSearchParam param){
        List list=new ArrayList();
        switch (param.getType()){
            case 1:
                list = detailCollectMapper.cgscdetail(param);
                break;
            case 2:
                list=detailCollectMapper.tsxywDetail(param);
                break;
            case 3:
                list=detailCollectMapper.zsxywDetail(param);
                break;
            case 4:
                list=detailCollectMapper.jdzqDetail(param);
                break;
            case 5:
                list=detailCollectMapper.sedzqDetail(param);
                break;
            case 6:
                list=detailCollectMapper.cglhzDetail(param);
                break;
            case 7:
                list=detailCollectMapper.cglmxDetail(param);
                break;
            case 8:
                list=detailCollectMapper.issueDetail(param);
                break;
            case 9:
                list=detailCollectMapper.introspectionDetail(param);
                break;
            case 10:
                list=detailCollectMapper.allReport(param);
                break;
        }
        return list;
    }
}
