package com.hanergy.reportForms.commons.utils;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * @return
 * @name 导出数据
 */
public class ReturnExcel {


    //1521报表导出
    /*超过4次(含)未请假未提报明细表 type3
     * 提报月平均条数小于5明细表 type4
     * 提报月平均字数小于5明细表 type5
     * 9点之前 type6
     * 12点之前 type7
     * 提报内容重复超6次(含)汇总表 type8
     * 提报内容重复超6次（含）明细表 type9
     * 问题 type1
     * 反省 type2
     * */
    public static Boolean printFormsExcel(HttpServletResponse response, List<Map<String, Object>> list,String type) {
        Boolean flage = false;
        // 创建excel
        HSSFWorkbook wk = new HSSFWorkbook();
        // 创建一张工作表
        HSSFSheet sheet = wk.createSheet("stud1");
        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);
        Map<String,String>keyMap=creatCell(row,type);
        int index = 0;// 记录额外创建的sheet数量
        for (int i = 0; i < list.size(); i++) {
            if ((i + 1) % 65535 == 0) {
                index++;
                sheet = wk.createSheet("stud" + (index+1));
                row = sheet.createRow(0);
                creatCell(row,type);
            }
            row = sheet.createRow((i + 1) - (index * 65534));
            String value = null;
            for (int j=0;j<keyMap.size();j++) {
                if (list.get(i).get(keyMap.get("key"+j))!=null){
                    value=list.get(i).get(keyMap.get("key"+j)).toString();
                }
                row.createCell(j).setCellValue(value);
            }
        }
        try {
            /**
             * 弹出下载选择路径框
             */
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=Opinion.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.close();
            flage = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return flage;
    }
    public static Map<String,String>  creatCell(HSSFRow row,String type){
        Map<String,String> keyMap=new HashMap<>();
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("月份");
        if (type.equals("type4")||type.equals("type5")){
            keyMap.put("key0","in_month");
        }else if (type.equals("type3")){
            keyMap.put("key0","month1");
        }else {
            keyMap.put("key0","month");
        }

        cell = row.createCell(1);
        if (type.equals("type3")){
            cell.setCellValue("未提报日期");
            keyMap.put("key1","inputtime");
        }else {
            cell.setCellValue("日期");
            if (type.equals("type4")||type.equals("type5")){
                keyMap.put("key1","in_date");
            } else if (type.equals("type9")){
                keyMap.put("key1","day1");
            }else {
                keyMap.put("key1","day");
            }
        }

        cell = row.createCell(2);
        if (type.equals("type1")||type.equals("type2")||type.equals("type3")||type.equals("type8")||type.equals("type9")){
            cell.setCellValue("员工工号");
            keyMap.put("key2","employeeID");
        }else {
            cell.setCellValue("提报时间");
            if(type.equals("type4")||type.equals("type5")) {
                keyMap.put("key2", "in_datetime");
            }else if (type.equals("type6")||type.equals("type7")){
                keyMap.put("key2", "inputtime");
            }
        }
        cell = row.createCell(3);
        if (type.equals("type1")||type.equals("type2")||type.equals("type3")||type.equals("type8")||type.equals("type9")){
            cell.setCellValue("员工姓名");
            if (type.equals("type3")){
                keyMap.put("key3","realName");
            }else {
                keyMap.put("key3","empname");
            }
        }else {
            cell.setCellValue("员工工号");
            keyMap.put("key3","employeeID");
        }
        cell = row.createCell(4);
        if (type.equals("type1")||type.equals("type2")||type.equals("type3")||type.equals("type8")||type.equals("type9")){
            cell.setCellValue("部门");
            if (type.equals("type3")){
                keyMap.put("key4","frameName");
            }else {
                keyMap.put("key4","deptname");
            }
        }else {
            cell.setCellValue("员工姓名");
            if (type.equals("type4")||type.equals("type5")) {
                keyMap.put("key4", "empname");
            }else {
                keyMap.put("key4", "emp_name");
            }
        }
        if (!type.equals("type3")) {
            cell = row.createCell(5);
            if (type.equals("type1") || type.equals("type2") || type.equals("type9")) {
                cell.setCellValue("提报内容");
                if (type.equals("type1")){
                    keyMap.put("key5", "sol_pro");
                }else if (type.equals("type2")){
                    keyMap.put("key5", "introspect");
                }else {
                    keyMap.put("key5", "content_new"); 
                }
            }else if (type.equals("type8")){
                cell.setCellValue("重复次数");
                keyMap.put("key5", "ts");
            }else {
                cell.setCellValue("部门");
                if (type.equals("type4")||type.equals("type5")){
                    keyMap.put("key5", "departmentname");
                }else {
                    keyMap.put("key5", "dept_name");
                }
            }
        }
        if (type.equals("type4")||type.equals("type5")||type.equals("type6")||type.equals("type7")||type.equals("type8")){
            cell = row.createCell(6);
            cell.setCellValue("提报内容");
            if (type.equals("type8")){
                keyMap.put("key6", "content");
            }else {
                keyMap.put("key6", "content_new");
            }
        }
        return keyMap;
    }

    //产供销预警详情导出
    public static Boolean printProductionExcel(HttpServletResponse response, List<Map<String, Object>> list) {
        Boolean flage = false;
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet("stud1");
        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);
        // 创建第一行的第一个单元格
        // 想单元格写值
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("采购申请号");
        cell = row.createCell(1);
        cell.setCellValue("事业部");
        cell = row.createCell(2);
        cell.setCellValue("物料号");
        cell = row.createCell(3);
        cell.setCellValue("物料描述");
        cell = row.createCell(4);
        cell.setCellValue("提交人");
        cell = row.createCell(5);
        cell.setCellValue("最新阶段 ");
        cell = row.createCell(6);
        cell.setCellValue("最新计划");
        cell = row.createCell(7);
        cell.setCellValue("到期 ");

        int index = 0;// 记录额外创建的sheet数量
        for (int i = 0; i < list.size(); i++) {
            if ((i + 1) % 65535 == 0) {
                index++;
                sheet = wk.createSheet("stud" + (index+1));
                row = sheet.createRow(0);
                row.createCell(0).setCellValue("采购申请");
                row.createCell(1).setCellValue("事业部");
                row.createCell(2).setCellValue("物料号");
                row.createCell(3).setCellValue("物料描述");
                row.createCell(4).setCellValue("提交人");
                row.createCell(5).setCellValue("最新阶段");
                row.createCell(6).setCellValue("最新计划");
                row.createCell(7).setCellValue("到期");
            }
            row = sheet.createRow((i + 1) - (index * 65534));

            String value = null;
            for (String key : list.get(i).keySet()) {
                Object obj = list.get(i).get(key);
                if (obj != null) {
                    value = obj.toString();
                }
                if (key.equals("FD_CAIGOUSHENQING")) {
                    row.createCell(0).setCellValue(value);
                }
                if (key.equals("fd_shiyebu")) {
                    row.createCell(1).setCellValue(value);
                }
                if (key.equals("FD_WULIAOBIANHAO")) {
                    row.createCell(2).setCellValue(value);
                }
                if (key.equals("FD_WULIAOMIAOSHU")) {
                    row.createCell(3).setCellValue(value);
                }
                if (key.equals("fd_name")) {
                    row.createCell(4).setCellValue(value);
                }
                if (key.equals("new_stage")) {
                    row.createCell(5).setCellValue(value);
                }
                if (key.equals("new_jihua")) {
                    row.createCell(6).setCellValue(value);
                }
                if (key.equals("daoqi")) {
                    row.createCell(7).setCellValue(value);
                }
            }
        }

        try {
            /**
             * 弹出下载选择路径框
             */
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=Opinion.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.close();
            flage = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flage;
    }

    //全员提报数据明细
    public static void allReportExport(HttpServletResponse response, List<LinkedHashMap<String, Object>> list) {
        List<String>rowValue= Arrays.asList
                ("月份","日期","提报时间","员工工号","职级","员工姓名","部门","控股集团","事业群","1-发现并解决问题",
                "2-工作","3-工作","4-工作","5-工作","6-发现并解决问题","7-客户","8-工作","9-工作","10-工作","11-工作",
                "12-工作","13-工作","14-工作","15-工作","16-反省");
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet(/*sheetNum*/"sheet1");
        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);
        for (int i=0;i<rowValue.size();i++){
            row.createCell(i).setCellValue(rowValue.get(i));
        }
        Integer index=0;
        for (int i = 0; i < list.size(); i++) {
            if ((i+1)%65535==0){
                index++;
                sheet = wk.createSheet("sheet"+(index+1));
                row = sheet.createRow(0);
                for (int a=0;a<rowValue.size();a++){
                    row.createCell(a).setCellValue(rowValue.get(a));
                }
            }
            row = sheet.createRow((i + 1)-index*65534);
            Iterator<Map.Entry<String,Object>>iterator=list.get(i).entrySet().iterator();
            int j=0;
            while (iterator.hasNext()){
                String value=iterator.next().getValue().toString();
                row.createCell(j).setCellValue(value);
                j++;
            }
        }

        try {
            /**
             * 弹出下载选择路径框
             */
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=Opinion.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*人员流量明细*/
    public static void personnelFlowExport(HttpServletResponse response, List<Map<String, Object>> list) {
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet("sheet1");
        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("负责人");
        cell = row.createCell(1);
        cell.setCellValue("应聘负责人");
        cell = row.createCell(2);
        cell.setCellValue("候选人姓名");
        cell = row.createCell(3);
        cell.setCellValue("候选人工号");
        cell = row.createCell(4);
        cell.setCellValue("候选人身份证");
        cell = row.createCell(5);
        cell.setCellValue("应聘职位");
        cell = row.createCell(6);
        cell.setCellValue("推荐职级");
        cell = row.createCell(7);
        cell.setCellValue("应聘集团");
        cell = row.createCell(8);
        cell.setCellValue("应聘部门/事业部");
        cell = row.createCell(9);
        cell.setCellValue("工作地点");
        cell = row.createCell(10);
        cell.setCellValue("入职后汇报领导");
        cell = row.createCell(11);
        cell.setCellValue("简历来源");
        cell = row.createCell(12);
        cell.setCellValue("简历日期");
        cell = row.createCell(13);
        cell.setCellValue("offer日期");
        cell = row.createCell(14);
        cell.setCellValue("预计入职日期");
        cell = row.createCell(15);
        cell.setCellValue("入职日期");
        int index = 0;// 记录额外创建的sheet数量
        for (int i = 0; i < list.size(); i++) {
            if ((i + 1) % 65535 == 0) {
                index++;
                sheet = wk.createSheet("sheet" + (index+1));
                row = sheet.createRow(0);
                row.createCell(0).setCellValue("负责人");
                row.createCell(1).setCellValue("应聘负责人");
                row.createCell(2).setCellValue("候选人姓名");
                row.createCell(3).setCellValue("候选人工号");
                row.createCell(4).setCellValue("候选人身份证");
                row.createCell(5).setCellValue("应聘职位");
                row.createCell(6).setCellValue("推荐职级");
                row.createCell(7).setCellValue("应聘集团");
                row.createCell(8).setCellValue("应聘部门/事业部");
                row.createCell(9).setCellValue("工作地点");
                row.createCell(10).setCellValue("入职后汇报领导");
                row.createCell(11).setCellValue("简历来源");
                row.createCell(12).setCellValue("简历日期");
                row.createCell(13).setCellValue("offer日期");
                row.createCell(14).setCellValue("预计入职日期");
                row.createCell(15).setCellValue("入职日期");
            }
            row = sheet.createRow((i + 1) - (index * 65534));

            for (String key : list.get(i).keySet()) {
                String value = "";
                Object object = list.get(i).get(key);
                if (object != null) {
                    value = object.toString();
                }
                if (key.equals("approver")) {//负责人
                    row.createCell(0).setCellValue(value);
                }
                if (key.equals("recruiter")) {//应聘负责人
                    row.createCell(1).setCellValue(value);
                }
                if (key.equals("candidateName")) {//候选人姓名
                    row.createCell(2).setCellValue(value);
                }
                if (key.equals("positionNumber")) {//候选人工号
                    row.createCell(3).setCellValue(value);
                }
                if (key.equals("nationalidcardmun")) {//候选人身份证
                    row.createCell(4).setCellValue(value);
                }
                if (key.equals("jobTitle")) {//应聘职位
                    row.createCell(5).setCellValue(value);
                }
                if (key.equals("jobLevel")) {//推荐职级
                    row.createCell(6).setCellValue(value);
                }
                if (key.equals("buoncode")) {//应聘集团
                    row.createCell(7).setCellValue(value);
                }
                if (key.equals("divicode")) {//应聘部门/事业部
                    row.createCell(8).setCellValue(value);
                }
                if (key.equals("locationcode")) {//工作地点
                    row.createCell(9).setCellValue(value);
                }
                if (key.equals("hiringManager")) {//入职后汇报领导
                    row.createCell(10).setCellValue(value);
                }
                if (key.equals("resumeSource")) {//简历来源
                    row.createCell(11).setCellValue(value);
                }
                if (key.equals("talkSalaryDate")) {//简历日期
                    row.createCell(12).setCellValue(value);
                }
                if (key.equals("offerDate")) {//offer日期
                    row.createCell(13).setCellValue(value);
                }
                if (key.equals("explanJobDate")) {//预计入职日期
                    row.createCell(14).setCellValue(value);
                }
                if (key.equals("jobDate")) {//入职日期
                    row.createCell(15).setCellValue(value);
                }
            }
        }

        try {
            /**
             * 弹出下载选择路径框
             */
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=Opinion.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //问题提出解决列表excel导出
    public static Boolean problemDetailedExcel(HttpServletResponse response, List<Map<String, Object>> list) {
        Boolean flage = false;
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet("sheet1");
        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("提出部门");
        cell = row.createCell(1);
        cell.setCellValue("提出人");
        cell = row.createCell(2);
        cell.setCellValue("提出部门负责人");
        cell = row.createCell(3);
        cell.setCellValue("提出时间");
        cell = row.createCell(4);
        cell.setCellValue("问题类型");
        cell = row.createCell(5);
        cell.setCellValue("问题状态");
        cell = row.createCell(6);
        cell.setCellValue("是否实施");
        cell = row.createCell(7);
        cell.setCellValue("问题描述 ");
        cell = row.createCell(8);
        cell.setCellValue("建议");
        cell = row.createCell(9);
        cell.setCellValue("承办部门");
        cell = row.createCell(10);
        cell.setCellValue("承办部门负责人");
        cell = row.createCell(11);
        cell.setCellValue("承办人");
        cell = row.createCell(12);
        cell.setCellValue("完成时间");
        cell = row.createCell(13);
        cell.setCellValue("实施措施及结果说明");
        int index = 0;// 记录额外创建的sheet数量
        for (int i = 0; i < list.size(); i++) {
            if ((i + 1) % 65535 == 0) {
                index++;
                sheet = wk.createSheet("sheet" + (index+1));
                row = sheet.createRow(0);
                row.createCell(0).setCellValue("提出部门");
                row.createCell(1).setCellValue("提出人");
                row.createCell(2).setCellValue("提出部门负责人");
                row.createCell(3).setCellValue("提出时间");
                row.createCell(4).setCellValue("问题类型");
                row.createCell(5).setCellValue("问题状态");
                row.createCell(6).setCellValue("是否实施");
                row.createCell(7).setCellValue("问题描述");
                row.createCell(8).setCellValue("建议");
                row.createCell(9).setCellValue("承办部门");
                row.createCell(10).setCellValue("承办部门负责人");
                row.createCell(11).setCellValue("承办人");
                row.createCell(12).setCellValue("完成时间");
                row.createCell(13).setCellValue("实施措施及结果说明");

            }
            row = sheet.createRow((i + 1) - (index * 65534));

            for (String key : list.get(i).keySet()) {
                String value = "";
                Object object = list.get(i).get(key);
                if (object != null) {
                    value = object.toString();
                }
                if (key.equals("tichubumen")) {
                    row.createCell(0).setCellValue(value);
                }
                if (key.equals("tichuren")) {
                    row.createCell(1).setCellValue(value);
                }
                if (key.equals("tichubumenfuzeren")) {
                    row.createCell(2).setCellValue(value);
                }
                if (key.equals("shenqingriqi")) {
                    row.createCell(3).setCellValue(value);
                }
                if (key.equals("wentileixing")) {
                    row.createCell(4).setCellValue(value);
                }
                if (key.equals("chengbanrenquerenwentizhuan")) {
                    row.createCell(5).setCellValue(value);
                }
                if (key.equals("shifushishi")) {
                    row.createCell(6).setCellValue(value);
                }
                if (key.equals("wentimiaoshu")) {
                    row.createCell(7).setCellValue(value);
                }
                if (key.equals("jianyi")) {
                    row.createCell(8).setCellValue(value);
                }
                if (key.equals("chengbanbumen")) {
                    row.createCell(9).setCellValue(value);
                }
                if (key.equals("chengbanbumenfuzeren")) {
                    row.createCell(10).setCellValue(value);
                }
                if (key.equals("chengbanren")) {
                    row.createCell(11).setCellValue(value);
                }
                if (key.equals("wanchengshijian")) {
                    row.createCell(12).setCellValue(value);
                }
                if (key.equals("cuoshijishuoming")) {
                    row.createCell(13).setCellValue(value);
                }
            }
        }

        try {
            /**
             * 弹出下载选择路径框
             */
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=Opinion.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.close();
            flage = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flage;
    }

    public static Boolean processRecodeExcel(HttpServletResponse response, List<Map<String, Object>> list) {
        Boolean flage = false;
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet("sheet1");
        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("时间");
        cell = row.createCell(1);
        cell.setCellValue("用户名");
        cell = row.createCell(2);
        cell.setCellValue("姓名");
        cell = row.createCell(3);
        cell.setCellValue("登陆ip");
        cell = row.createCell(4);
        cell.setCellValue("操作对象");
        cell = row.createCell(5);
        cell.setCellValue("操作类型");
        cell = row.createCell(6);
        int index = 0;// 记录额外创建的sheet数量
        for (int i = 0; i < list.size(); i++) {
            if ((i + 1) % 65535 == 0) {
                index++;
                sheet = wk.createSheet("sheet" + (index+1));
                row = sheet.createRow(0);
                row.createCell(0).setCellValue("时间");
                row.createCell(1).setCellValue("用户名");
                row.createCell(2).setCellValue("姓名");
                row.createCell(3).setCellValue("登陆ip");
                row.createCell(4).setCellValue("操作对象");
                row.createCell(5).setCellValue("操作类型");
            }
            row = sheet.createRow((i + 1) - (index * 65534));

            for (String key : list.get(i).keySet()) {
                String value = "";
                Object object = list.get(i).get(key);
                if (object != null) {
                    value = object.toString();
                }
                if (key.equals("proTime")) {
                    row.createCell(0).setCellValue(value);
                }
                if (key.equals("userName")) {
                    row.createCell(1).setCellValue(value);
                }
                if (key.equals("fullName")) {
                    row.createCell(2).setCellValue(value);
                }
                if (key.equals("loginIpAddr")) {
                    row.createCell(3).setCellValue(value);
                }
                if (key.equals("proTarg")) {
                    row.createCell(4).setCellValue(value);
                }
                if (key.equals("proType")) {
                    row.createCell(5).setCellValue(value);
                }
            }
        }

        try

        {
            /**
             * 弹出下载选择路径框
             */
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=Opinion.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.close();
            flage = true;
        } catch (
                IOException e)

        {
            e.printStackTrace();
        } finally

        {
        }
        return flage;
    }

    //产供销详情页面
    public static Boolean printProductDetailedExcel(HttpServletResponse response, List<Map<String, Object>> list) {
        Boolean flage = false;
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet("stud1");
        sheet.setColumnWidth(0, 5000);
        HSSFRow row = sheet.createRow(0);
        // 创建第一行的第一个单元格
        // 想单元格写值
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("产品类型");
        cell = row.createCell(1);
        cell.setCellValue("采购申请号");
        cell = row.createCell(2);
        cell.setCellValue("事业部");
        cell = row.createCell(3);
        cell.setCellValue("物料号");
        cell = row.createCell(4);
        cell.setCellValue("物料描述");
        cell = row.createCell(5);
        cell.setCellValue("提交人");
        cell = row.createCell(6);
        cell.setCellValue("预警 ");
        cell = row.createCell(7);
        cell.setCellValue("PR计划");
        cell = row.createCell(8);
        cell.setCellValue("PR实际");
        cell = row.createCell(9);
        cell.setCellValue("采购合同签署计划");
        cell = row.createCell(10);
        cell.setCellValue("采购合同签署变更");
        cell = row.createCell(11);
        cell.setCellValue("采购合同签署实际");
        cell = row.createCell(12);
        cell.setCellValue("预付款计划");
        cell = row.createCell(13);
        cell.setCellValue("预付款变更");
        cell = row.createCell(14);
        cell.setCellValue("预付款实际");
        cell = row.createCell(15);
        cell.setCellValue("生产计划");
        cell = row.createCell(16);
        cell.setCellValue("生产变更");
        cell = row.createCell(17);
        cell.setCellValue("生产实际");
        cell = row.createCell(18);
        cell.setCellValue("提货款计划");
        cell = row.createCell(19);
        cell.setCellValue("提货款变更");
        cell = row.createCell(20);
        cell.setCellValue("提货款实际");
        cell = row.createCell(21);
        cell.setCellValue("交付计划");
        cell = row.createCell(22);
        cell.setCellValue("交付变更");
        cell = row.createCell(23);
        cell.setCellValue("交付实际");
        int index = 0;// 记录额外创建的sheet数量
        for (int i = 0; i < list.size(); i++) {
            if ((i + 1) % 65535 == 0) {
                index++;
                sheet = wk.createSheet("stud" + (index+1));
                row = sheet.createRow(0);
                row.createCell(0).setCellValue("产品类型");
                row.createCell(1).setCellValue("采购申请号");
                row.createCell(2).setCellValue("事业部");
                row.createCell(3).setCellValue("物料号");
                row.createCell(4).setCellValue("物料描述");
                row.createCell(5).setCellValue("提交人");
                row.createCell(6).setCellValue("预警");
                row.createCell(7).setCellValue("PR计划");
                row.createCell(8).setCellValue("PR实际");
                row.createCell(9).setCellValue("采购合同签署计划");
                row.createCell(10).setCellValue("采购合同签署变更");
                row.createCell(11).setCellValue("采购合同签署实际");
                row.createCell(12).setCellValue("预付款计划");
                row.createCell(13).setCellValue("预付款变更");
                row.createCell(14).setCellValue("预付款实际");
                row.createCell(15).setCellValue("生产计划");
                row.createCell(16).setCellValue("生产变更");
                row.createCell(17).setCellValue("生产实际");
                row.createCell(18).setCellValue("提货款计划");
                row.createCell(19).setCellValue("提货款变更");
                row.createCell(20).setCellValue("提货款实际");
                row.createCell(21).setCellValue("交付计划");
                row.createCell(22).setCellValue("交付变更");
                row.createCell(23).setCellValue("交付实际");
            }
            row = sheet.createRow((i + 1) - (index * 65534));
            String value = null;
            for (String key : list.get(i).keySet()) {
                Object obj = list.get(i).get(key);
                if (obj != null) {
                    value = obj.toString();
                }
                if (key.equals("CHANPIN_NAME")) {
                    row.createCell(0).setCellValue(value);
                }
                if (key.equals("FD_CAIGOUSHENQING")) {
                    row.createCell(1).setCellValue(value);
                }
                if (key.equals("FD_SHIYEBU")) {
                    row.createCell(2).setCellValue(value);
                }
                if (key.equals("FD_WULIAOBIANHAO")) {
                    row.createCell(3).setCellValue(value);
                }
                if (key.equals("FD_WULIAOMIAOSHU")) {
                    row.createCell(4).setCellValue(value);
                }
                if (key.equals("FD_NAME")) {
                    row.createCell(5).setCellValue(value);
                }
                if (key.equals("WARNING")) {
                    row.createCell(6).setCellValue(value);
                }
                if (key.equals("PRJIHUA")) {
                    row.createCell(7).setCellValue(value);
                }
                if (key.equals("PRSHIJI")) {
                    row.createCell(8).setCellValue(value);
                }
                if (key.equals("CAIGOUJIHUA")) {
                    row.createCell(9).setCellValue(value);
                }
                if (key.equals("CAIGOUBIANGENG")) {
                    row.createCell(10).setCellValue(value);
                }
                if (key.equals("CAIGOUSHIJI")) {
                    row.createCell(11).setCellValue(value);
                }
                if (key.equals("YUFUKUANJIHUA")) {
                    row.createCell(12).setCellValue(value);
                }
                if (key.equals("YUFUKUANBIANGENG")) {
                    row.createCell(13).setCellValue(value);
                }
                if (key.equals("YUFUKUANSHIJI")) {
                    row.createCell(14).setCellValue(value);
                }
                if (key.equals("SHENGCHANJIHUA")) {
                    row.createCell(15).setCellValue(value);
                }
                if (key.equals("SHENGCHANBIANGENG")) {
                    row.createCell(16).setCellValue(value);
                }
                if (key.equals("SHENGCHANSHIJI")) {
                    row.createCell(17).setCellValue(value);
                }
                if (key.equals("TIHUOKUANJIHUA")) {
                    row.createCell(18).setCellValue(value);
                }
                if (key.equals("TIHUOKUANBIANGENG")) {
                    row.createCell(19).setCellValue(value);
                }
                if (key.equals("TIHUOKUANSHIJI")) {
                    row.createCell(20).setCellValue(value);
                }
                if (key.equals("JIAOFUJIHUA")) {
                    row.createCell(21).setCellValue(value);
                }
                if (key.equals("JIAOFUBIANGENG")) {
                    row.createCell(22).setCellValue(value);
                }
                if (key.equals("JIAOFUSHIJI")) {
                    row.createCell(23).setCellValue(value);
                }
            }
        }

        try {
            /**
             * 弹出下载选择路径框
             */
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=Opinion.xls");//默认Excel名称
            response.flushBuffer();
            wk.write(response.getOutputStream());
            wk.close();
            flage = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return flage;
    }


}