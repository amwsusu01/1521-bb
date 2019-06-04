package com.hanergy.reportForms.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hanergy.reportForms.commons.enums.EnumCancel;
import com.hanergy.reportForms.commons.enums.EnumPhaseStatusToCheckStatus;
import com.hanergy.reportForms.commons.enums.EnumPostType;
import com.hanergy.reportForms.commons.enums.EnumWorkType;

public class MatchUtil {
	public static Map<Integer ,String >   TEMPLATE_POST_TYPE_MAP = new HashMap<Integer,String>();
	public static Map<Integer ,String >   TEMPLATE_WORK_TYPE_MAP = new HashMap<Integer,String>();
	public static Map<Integer ,String >   TEMPLATE_CANCEL_MAP = new HashMap<Integer,String>();
	public static Map<Integer ,String >   TEMPLATE_INVEST_UNIT_MAP = new HashMap<Integer,String>();
	
	public static List<Integer>  BESELECTED_TYPE_LIST = new ArrayList<>();
	/**
	 * 前台查询候选人列表对应关系
	 */
	public static Map<Integer ,EnumPhaseStatusToCheckStatus >   CHECK_STATUS_TO_PHASE_STATUE_MAP = new HashMap<Integer ,EnumPhaseStatusToCheckStatus>();

	public static List<Long> RANK_LIST = new ArrayList<Long>();
	

	static {
		TEMPLATE_POST_TYPE_MAP.putAll(EnumPostType.getMap());
		
		TEMPLATE_WORK_TYPE_MAP.putAll(EnumWorkType.getMap());
		
		RANK_LIST.add(0L);RANK_LIST.add(1L);RANK_LIST.add(2L);RANK_LIST.add(3L);
		RANK_LIST.add(4L);RANK_LIST.add(5L);RANK_LIST.add(6L);RANK_LIST.add(7L);
		RANK_LIST.add(8L);RANK_LIST.add(9L);RANK_LIST.add(10L);RANK_LIST.add(11L);
		RANK_LIST.add(12L);RANK_LIST.add(13L);RANK_LIST.add(14L);RANK_LIST.add(15L);
		RANK_LIST.add(16L);RANK_LIST.add(17L);RANK_LIST.add(18L);RANK_LIST.add(19L);
		RANK_LIST.add(20L);RANK_LIST.add(21L);RANK_LIST.add(22L);RANK_LIST.add(23L);
		RANK_LIST.add(24L);RANK_LIST.add(25L);RANK_LIST.add(26L);RANK_LIST.add(27L);
		RANK_LIST.add(28L);RANK_LIST.add(29L);RANK_LIST.add(30L);
		
		TEMPLATE_CANCEL_MAP.putAll(EnumCancel.getMap());
		
		TEMPLATE_INVEST_UNIT_MAP.put(0, "负责人");
		TEMPLATE_INVEST_UNIT_MAP.put(1, "背调公司");
		// 查询类型 1：本月已完成状态 ;2:正1在进行中 ;4:待审批 ;5:资料填写（招聘负责人补充候选人资料） ;"
		// "6:待审核; 7:候选人授权; 8:候选人提交资料 ; 9:联机调查; 10:工作经历核查 11:待委托 12:调查中;13：声名签署;14:入职后核查;15:结果确认
		BESELECTED_TYPE_LIST.add(1);
		BESELECTED_TYPE_LIST.add(2);
		BESELECTED_TYPE_LIST.add(4);
		BESELECTED_TYPE_LIST.add(5);
		BESELECTED_TYPE_LIST.add(6);
		BESELECTED_TYPE_LIST.add(7);
		BESELECTED_TYPE_LIST.add(8);
		BESELECTED_TYPE_LIST.add(9);
		BESELECTED_TYPE_LIST.add(10);
		BESELECTED_TYPE_LIST.add(11);
		BESELECTED_TYPE_LIST.add(12);
		BESELECTED_TYPE_LIST.add(13);
		BESELECTED_TYPE_LIST.add(14);
		BESELECTED_TYPE_LIST.add(15);
	}
	
	public static String getTemplatePostType(Integer integer){
		return TEMPLATE_POST_TYPE_MAP.get(integer);
	}
	public static String getTemplateWorkType(Integer key){
		return TEMPLATE_WORK_TYPE_MAP.get(key);
	}
	
	public static List<Integer> getCheckStatusList(String msg){
		List<Integer> list = new ArrayList<>();
		EnumPhaseStatusToCheckStatus enumPhaseState = EnumPhaseStatusToCheckStatus.getEnum(msg);
		for(Entry<Integer, EnumPhaseStatusToCheckStatus>  obj :CHECK_STATUS_TO_PHASE_STATUE_MAP.entrySet()){
			if(enumPhaseState == obj.getValue()){
				list.add(obj.getKey());
			}
		}
		return list;
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(getCheckStatusList("自主"));
	}
}
