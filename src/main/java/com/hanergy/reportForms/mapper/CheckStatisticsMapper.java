package com.hanergy.reportForms.mapper;

import com.hanergy.reportForms.mapper.entity.CheckStatistics;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fangshuai
 * @since 2018-10-16
 */
public interface CheckStatisticsMapper extends BaseMapper<CheckStatistics> {
		
		Integer getCheckStatisticsByIdArr(@Param("statusArr")List<Integer> statusArr,
				@Param("userId")Long userId);
		/**
		 * operation  当为1时执行加一操作，当为0时执行减一操作
		 * @param operation
		 * @param checkStatus
		 * @param userId
		 * @return
		 */
		int updateCheckStatistcsCountByUserIdStatus(@Param("operation")Integer operation,@Param("checkStatus")List<Integer> checkStatusArr,
				@Param("userId")Long userId);
}
