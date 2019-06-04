package com.hanergy.reportForms.mapper.detailcollect;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.commons.CommonSearchParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DetailCollectMapper {

    /*超过4次(含)未请假未提报明细表*/
    public List<Map> cgscdetail(CommonSearchParam param);
    public Integer countCgscdetail(CommonSearchParam param);

    /*提报月平均条数小于5明细表*/
    public List<Map> tsxywDetail(CommonSearchParam param);
    public Integer countTsxywDetail(CommonSearchParam param);

    /*提报月平均字数小于5明细表*/
    public List<Map> zsxywDetail(CommonSearchParam param);
    public Integer countZsxywDetail(CommonSearchParam param);

    /*9点之前提报数据明细表*/
    public List<Map> jdzqDetail(CommonSearchParam param);
    public Integer countJdzqDetail(CommonSearchParam param);

    /*12点之前提报数据明细表*/
    public List<Map> sedzqDetail(CommonSearchParam param);
    public Integer countSedzqDetail(CommonSearchParam param);

    /*提报内容重复超6次(含)汇总表*/
    public List<Map> cglhzDetail(CommonSearchParam param);
    public Integer countCglhzDetail(CommonSearchParam param);

    /*提报内容重复超6次（含）明细表*/
    public List<Map> cglmxDetail(CommonSearchParam param);
    public Integer countCglmxDetail(CommonSearchParam param);

    /*部门问题明细表*/
    public List<Map> issueDetail(CommonSearchParam param);
    public Integer countIssueDetail(CommonSearchParam param);

    /*部门反省明细表*/
    public List<Map> introspectionDetail(CommonSearchParam param);
    public Integer countIntrospectionDetail(CommonSearchParam param);

    /*全员提报数据明细*/
    public List<Map> allReport(CommonSearchParam param);
    public Integer countAllReport(CommonSearchParam param);

    /**
     * 获取所有控股集团以及集团下的所有事业群，事业部，省公司，以及部门
     */
    public List<Map<String,String>> getAllOrganization(@Param("konggujituan") String konggujituan, @Param("shiyequn")String shiyequn, @Param("shiyebu")String shiyebu, @Param("shenggongsi")String shenggongsi);
}
