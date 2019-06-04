package com.hanergy.reportForms.service;

import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.entity.entrust.report.EntrustReportParamsResp;
import com.hanergy.reportForms.entity.entrust.report.UpLoadReportEntity;
import com.hanergy.reportForms.entity.entrust.report.UpLoadReportInfo;

/**
 * <p>
 * 功能 服务类
 * </p>
 *
 * @author 房帅
 * @since 2018-09-12
 */
public interface IEntrsutReportService  {
	
	
	/**
	 * 根据参数条件查询候选人背调记录列表（每个参数都为非必填项）
	 * @param entrustPage 
	 * @param type            1 ：待完成    ;  2：本月已完成
	 * @param verify_unit     中介id (核实单位：来源为人力则为用户表主键，来源为中介则为中介表主键)
	 * @param verifySources   中介类型 (核实来源：1候选人   2人力    3中介)
	 * @param page            当前页
	 * @param pageSize        每页数量
	 * @param checkStatus     背调状态：0未进行   1完成    2背调    3审批 (一般为2，待定)
	 * @param beSelected	  候选人id 
	 * @param keyword         关键字（支持模糊查询）
	 * @return
	 */
	JSONObject getEntrustReportByAgency(Integer type,Long verify_unit,Integer verifySources ,Integer page ,Integer pageSize,Integer checkStatus,Long beSelected,String keyword);


	/**
	 * 获取对应背调记录的检查项 （不包含背调负责人查看）
	 * @param check_id 背调记录id(必填)
	 * @param watchRole  查看报告的角色（0：招聘负责人 ；1：背调负责人 ; 2：第三方）
	 * @param verify_unit   (核实单位：来源为人力则为用户表主键，来源为中介则为中介表主键)
	 * @return
	 */
	EntrustReportParamsResp getEntrustReportParams(Long check_id, Long verify_unit);
	
	
	
	
	/**
	 * 获取报告详情（不包括背调负责人查看）
	 * @param check_id     背调id
	 * @param candidateId  候选人id
	 * @param queryType    查询类型  2：背调负责人查询详情，1：查询其他角色查询详情
	 * @return
	 */
	EntrustReportParamsResp getEntrustReportDetail(Long check_id,Long candidateId,Integer queryType )throws Exception;

	/**
	 * 获取背调负责人检查项
	 * @param candidateId   候选人id
	 * @param watchRole    查看报告的角色（0：招聘负责人 ；1：背调负责人 ; 2：第三方）
	 * @return
	 */
	EntrustReportParamsResp getBackCheckReportParams(Long candidateId);



	
	/**
	 * 上传报告
	 * @param upLoadReportInfo
	 * @return
	 */
	JSONObject upLoadReport(UpLoadReportInfo upLoadReportInfo);
	
	

	
	/**
	 * 专门用户联调第三方的报告上传接口
	 * @param upLoadReportInfo
	 * @return
	 */
	JSONObject upLoadEntrsutReport(UpLoadReportEntity upLoadReportEntity);


	JSONObject getEntrustReportStatistics(Long verifyUnit);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
