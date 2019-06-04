package com.hanergy.reportForms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanergy.reportForms.dto.third.MediCadidate;
import com.hanergy.reportForms.mapper.entity.BeSelected;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 候选人 Mapper 接口
 * </p>
 *
 * @author duronghong
 * @since 2018-09-20
 */
public interface BeSelectedMapper extends BaseMapper<BeSelected> {


    MediCadidate getMediCandidateById(@Param("beSelectId")Long beSelectId);
    
    
	/**
	 * 根据核实检查项的核实等级获取候选人列表
	 * @param userId      如何填入该参数，怎查询该userId关联的候选人数据
	 * @param verifySource
	 * @param itemArr
	 * @param verifyLevel
	 * @param keywords
	 * @param start
	 * @param end
	 * @return
	 */
    List<BeSelected>  getStageListByItemArr(@Param("userId")Long userId,@Param("verifySource") Integer verifySource,
    		@Param("itemArr")List<Long> itemArr,@Param("verifyLevel")Integer verifyLevel,@Param("keyword") String keywords,
    		@Param("start")Integer start,
    		@Param("end")Integer end);
    
	 /**
	  * 根据核实检查项的核实等级获取候选人列表总数
	  * @param userId      如何填入该参数，怎查询该userId关联的候选人数据
	  * @param verifySource
	  * @param itemArr
	  * @param verifyLevel
	  * @param keywords
	  * @return
	  */
    Integer getStageCountByItemArr(@Param("userId")Long userId,@Param("verifySource") Integer verifySource,
    		@Param("itemArr")List<Long> itemArr,@Param("verifyLevel")Integer verifyLevel,
    		@Param("keyword") String keywords);

    void updateCheckStatus(@Param("beSelectedId") Long beSelectedId, @Param("checkStatus") Integer checkStatus);
    
}
