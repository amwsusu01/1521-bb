package com.hanergy.reportForms.commons.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hanergy.reportForms.mapper.entity.BeSelected;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterUtil {
	 public static void main(String[] args) {
		//
	}
	/**
	 * 对应招聘负责人的查询条件
	 * @param type
	 * @return
	 */
	 public   static QueryWrapper<BeSelected> getRECRUITCheckStatusByType(Integer type){
    	 QueryWrapper<BeSelected> queryWrapper = new QueryWrapper<>();
    	 List<Integer> list = new ArrayList<>();
    	switch ( type) {
    	//本月已完成状态
		case 1:
			Date[] period = DateUtils.formatCurrentAllMonth();
			queryWrapper.eq("check_status", 1);
			queryWrapper.between("check_status_time", period[0], period[1]);
			break;
		//正在进行中
		case 2:
			list.add(0);
			list.add(2);
			list.add(3);
			list.add(4);
			list.add(5);
			list.add(6);
			list.add(7);
			list.add(8);
			list.add(9);
			list.add(10);
			queryWrapper.in("check_status", list);
			break;
			//待核实
		case 3:
			break;
		case 4:
			//待审批
			//包括业务负责人审批和hrvp审批
			queryWrapper.eq("staff_status2", 0);
			queryWrapper.or().eq("hrvp_status", 0);
			
			break;
		case 5:
			//资料填写（招聘负责人补充候选人资料）
			queryWrapper.eq("check_status", 0);
			break;
		case 6:
			//待审核
		    queryWrapper.eq("staff_status3", 0);
			//queryWrapper.eq("check_status", 7);
			break;
		case 7:
			//候选人授权
			queryWrapper.eq("check_status", 4);
			break;
		case 8:
			//候选人提交资料
			queryWrapper.eq("check_status", 5);
 			queryWrapper.isNull("self_check_status");
			break;
		case 9:
			//联机调查
			list.add(5);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("self_check_status", 211);
			break;
		case 10:
			//工作经历核查
			list.add(5);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("self_check_status", 212);
			break;
		case 11:
			//待委托 
			list.add(4);
			list.add(5);
			list.add(6);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("agency_check_status", 221);
			break;
		case 12:
			//调查中
			list.add(4);
			list.add(5);
			list.add(6);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("agency_check_status", 222);
			break;
		case 13:
			//调查中
			queryWrapper.eq("sign_statement_status", 111);
			break;
		case 14:
			queryWrapper.eq("post_entry_verify", 121);
			break;
		case 15:
			/*List<Integer> checkStatusArr = new ArrayList<>();
			checkStatusArr.add(7);
			checkStatusArr.add(8);
			queryWrapper.in("check_status", checkStatusArr);
			List<Integer> verifyLevelArr = new ArrayList<>();
			verifyLevelArr.add(2);
			verifyLevelArr.add(3);
			queryWrapper.in("verify_level", verifyLevelArr);*/
			queryWrapper.eq("staff_status", 0);
			
			break;
		default:
			break;
		}
		return queryWrapper;
    	
    }
	 /**
	  * hrvp负责人对应的查询条件
	  * @param type
	  * @return
	  */
	 public   static QueryWrapper<BeSelected> getHRVPCheckStatusByType(Integer type){
    	 QueryWrapper<BeSelected> queryWrapper = new QueryWrapper<>();
    	 List<Integer> list = new ArrayList<>();
    	switch ( type) {
    	//本月已完成状态
		case 1:
			Date[] period = DateUtils.formatCurrentAllMonth();
			queryWrapper.eq("check_status", 1);
			queryWrapper.between("check_status_time", period[0], period[1]);
			break;
		//正在进行中
		case 2:
			list.add(0);
			list.add(2);
			list.add(3);
			list.add(4);
			list.add(5);
			list.add(6);
			list.add(7);
			list.add(8);
			list.add(9);
			list.add(10);
			queryWrapper.in("check_status",list);
			break;
			//待核实
		case 3:
			break;
		case 4:
			//queryWrapper.eq("staff_result2", 1);
			queryWrapper.eq("hrvp_status", 0);
			break;
		case 5:
			//资料填写（招聘负责人补充候选人资料）
			queryWrapper.eq("check_status", 0);
			break;
		case 6:
			//待审核
			//queryWrapper.eq("check_status", 7);
			queryWrapper.eq("staff_status3", 0);
			break;
		case 7:
			//候选人授权
			queryWrapper.eq("check_status", 4);
			break;
		case 8:
			//候选人提交资料
			queryWrapper.eq("check_status", 5);
 			queryWrapper.isNull("self_check_status");
			break;
		case 9:
			//联机调查
			list.add(5);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("self_check_status", 211);
			break;
		case 10:
			//工作经历核查
			list.add(5);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("self_check_status", 212);
			break;
		case 11:
			//待委托 
			list.add(4);
			list.add(5);
			list.add(6);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("agency_check_status", 221);
			break;
		case 12:
			//调查中
			list.add(4);
			list.add(5);
			list.add(6);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("agency_check_status", 222);
			break;
		case 13:
			//调查中
			queryWrapper.eq("sign_statement_status", 111);
			break;
		case 14:
			queryWrapper.eq("post_entry_verify", 121);
			break;
		case 15:
			/*List<Integer> checkStatusArr = new ArrayList<>();
			checkStatusArr.add(7);
			checkStatusArr.add(8);
			queryWrapper.in("check_status", checkStatusArr);
			List<Integer> verifyLevelArr = new ArrayList<>();
			verifyLevelArr.add(2);
			verifyLevelArr.add(3);
			queryWrapper.in("verify_level", verifyLevelArr);*/
			queryWrapper.eq("staff_status", 0);
			break;
		default:
			break;
		}
		return queryWrapper;
    	
    }
	 
	 
	 /**
	  * 背调负责人对应的查询条件
	  * @param type
	  * @return
	  */
	 public   static QueryWrapper<BeSelected> getBackgroudCheckStatusByType(Integer type){
    	 QueryWrapper<BeSelected> queryWrapper = new QueryWrapper<>();
    	 List<Integer> list = new ArrayList<>();
    	switch ( type) {
    	//本月已完成状态
		case 1:
			Date[] period = DateUtils.formatCurrentAllMonth();
			queryWrapper.eq("check_status", 1);
			queryWrapper.between("check_status_time", period[0], period[1]);
			break;
		//正在进行中
		case 2:
			list.add(0);
			list.add(2);
			list.add(3);
			list.add(4);
			list.add(5);
			list.add(6);
			list.add(7);
			list.add(8);
			list.add(9);
			list.add(10);
			queryWrapper.in("check_status", list);
			break;
			//待核实
		case 3:
			break;
		case 4:
			//hrvp和招聘负责人审批
			/*list.add(3);
			list.add(9);*/
			//queryWrapper.in("check_status", list );
			queryWrapper.eq("staff_status2", 0);
			queryWrapper.or().eq("hrvp_status", 0);
			break;
		case 5:
			//资料填写（招聘负责人补充候选人资料）
			queryWrapper.eq("check_status", 0);
			break;
		case 6:
			//待审核
			//queryWrapper.eq("check_status", 7);
			queryWrapper.eq("staff_status3", 0);
			break;
		case 7:
			//候选人授权
			queryWrapper.eq("check_status", 4);
			break;
		case 8:
			//候选人提交资料
 			queryWrapper.eq("check_status", 5);
 			queryWrapper.isNull("self_check_status");
			break;
		case 9:
			//联机调查
			list.add(5);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("self_check_status", 211);
			break;
		case 10:
			//工作经历核查
			list.add(5);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("self_check_status", 212);
			break;
		case 11:
			//待委托 
			list.add(4);
			list.add(5);
			list.add(6);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("agency_check_status", 221);
			break;
		case 12:
			//调查中
			list.add(4);
			list.add(5);
			list.add(6);
			queryWrapper.in("check_status",list);
			queryWrapper.eq("agency_check_status", 222);
			break;
		case 13:
			//调查中
			queryWrapper.eq("sign_statement_status", 111);
			break;
		case 14:
			queryWrapper.eq("post_entry_verify", 121);
			break;
		case 15:
	/*		List<Integer> checkStatusArr = new ArrayList<>();
			checkStatusArr.add(7);
			checkStatusArr.add(8);
			queryWrapper.in("check_status", checkStatusArr);
			List<Integer> verifyLevelArr = new ArrayList<>();
			verifyLevelArr.add(2);
			verifyLevelArr.add(3);
			queryWrapper.in("verify_level", verifyLevelArr);*/
			queryWrapper.eq("staff_status", 0);
			break;
		default:
			break;
		}
		return queryWrapper;
    	
    }
	 
	 public static <T>void fatherToChild(T father,T child) throws Exception {
	        if (child.getClass().getSuperclass()!=father.getClass()){
	            throw new Exception("child 不是 father 的子类");
	        }
	        Class<?> fatherClass = father.getClass();
	        Field[] declaredFields = fatherClass.getDeclaredFields();
	        for (int i = 0; i < declaredFields.length; i++) {
	        	 Field field=declaredFields[i];
	        	if("serialVersionUID".equals(field.getName())){
	        		continue;
	        	}
	            Method method=fatherClass.getDeclaredMethod("get"+upperHeadChar(field.getName()));
	            Object obj = method.invoke(father);
	            field.setAccessible(true);
	            field.set(child,obj);
	        }

	    }

	    /**
	     * 首字母大写，in:deleteDate，out:DeleteDate
	     */
	    public static String upperHeadChar(String in) {
	        String head = in.substring(0, 1);
	        String out = head.toUpperCase() + in.substring(1, in.length());
	        return out;
	    }
}
