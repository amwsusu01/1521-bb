package com.hanergy.reportForms.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hanergy.reportForms.entity.entrust.report.CandidateReportEntity;
import com.hanergy.reportForms.entity.entrust.report.EntrustParamsInfo;

/**
 * @author fangshuai
 * @version 1.0.0
 * @time 2018年9月26日
 */
@Repository
public interface EntrustReportMapper {
	
		List<CandidateReportEntity>  getEntrustReportList(@Param("verify_unit") Long  verify_unit,
				@Param("verifyStatusArr")List<Integer>   verifyStatusArr ,
				@Param("checkStatusArr")List<Integer>   checkStatusArr ,
				@Param("agencyCheckStatus")Integer  agencyCheckStatus ,
				@Param("keyword") String   keyword,
				@Param("start") Integer   start,
				@Param("end") Integer   ends,
				@Param("startTime") Date   startTime,
				@Param("endTime") Date   endTime);

		List<EntrustParamsInfo> getEntrustParamInfo(@Param("check_id")Long check_id,
				@Param("verifySources") Integer verifySources, 
				@Param("verify_unit")Long verify_unit);
		
		List<EntrustParamsInfo> getBackCheckEntrustInfo(@Param("beSelected")Long beSelecteds,@Param("checkStatusArr") List<Integer> checkStatusArr);
		
		Integer getEntrustReportCount(@Param("verify_unit") Long  verify_unit,
				@Param("verifyStatusArr")List<Integer>    verifyStatusArr ,
				@Param("checkStatusArr")List<Integer>   checkStatusArr ,
				@Param("agencyCheckStatus")Integer  agencyCheckStatus ,
				@Param("keyword") String   keyword,
				@Param("startTime") Date   startTime,
				@Param("endTime") Date   endTime);
}
