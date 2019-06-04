package com.hanergy.reportForms.service.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.mapper.entity.Product;
import com.hanergy.reportForms.mapper.product.ProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List productList(Product product) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, String>> list = productMapper.productList(product);
        if (!list.isEmpty()) {
            List<String> proidList = new ArrayList();
            Map<String, Object> proMap = null;
            List monList = null;
            List monthList = null;
            for (Map<String, String> data : list) {
                List<Map<String, String>> wekList = new ArrayList<>();
                String proid = String.valueOf(data.get("proid"));
                if (!proidList.contains(proid)) {
                    proidList.add(proid);
                    proMap = new LinkedHashMap<>();
                    monthList = new ArrayList();
                }
                proMap.put("proid", proid);
                proMap.put("proName", data.get("proName"));
                proMap.put("targetDate", data.get("targetDate"));

                String month = data.get("month");
                if (!monthList.contains(month)) {
                    monthList.add(month);
                    monList = new ArrayList();
                }
                Map<String, Object> monMap = new LinkedHashMap<>();
                monMap.put("monDataId", data.get("monDataId"));
                monMap.put("monData", data.get("monData"));
                monMap.put("monDataType", data.get("monDataType"));
                monMap.put("month", month);
                String wekData = data.get("wekData");
                if (StringUtils.isNotBlank(wekData)) {
                    String[] wekArray = null;
                    List<String> weekList = new ArrayList<>();
                    if (wekData.indexOf(";") > 0) {
                        String[] array = wekData.split(";");
                        for (String fild : array) {
                            wekArray = fild.split(",");
                            Map<String, String> wekMap = new LinkedHashMap<>();
                            wekMap.put("wekDataId", wekArray[0]);
                            wekMap.put("wekData", wekArray[1]);
                            wekMap.put("week", wekArray[2]);
                            wekMap.put("wekDataType", wekArray[3]);
                            weekList.add(wekArray[2]);
                            wekList.add(wekMap);
                        }
                    } else {
                        wekArray = wekData.split(",");
                        Map<String, String> wekMap = new LinkedHashMap<>();
                        wekMap.put("wekDataId", wekArray[0]);
                        wekMap.put("wekData", wekArray[1]);
                        wekMap.put("week", wekArray[2]);
                        wekMap.put("wekDataType", wekArray[3]);
                        weekList.add(wekArray[2]);
                        wekList.add(wekMap);
                    }
                    if (wekList.size() < 5) {
                        List<String> weekParam = Arrays.asList(new String[]{"wk1", "wk2", "wk3", "wk4", "wk5"});
                        for (String str : weekList) {
                            weekParam = weekParam.stream().filter(a -> !a.equals(str)).collect(Collectors.toList());
                        }
                        for (String str : weekParam) {
                            Map<String, String> wekMap = new LinkedHashMap<>();
                            wekMap.put("wekDataId", "");
                            wekMap.put("wekData", "");
                            wekMap.put("week", str);
                            wekMap.put("wekDataType", wekArray[3]);
                            wekList.add(wekMap);
                        }
                    }
                    monMap.put("wekList", wekList);
                    monList.add(monMap);
                    proMap.put(data.get("month"), monList);
                }
                result.add(proMap);
            }
            result = result.stream().distinct().collect(Collectors.toList());
            //组装空数据
            for (Map<String, Object> pro : result) {
                List<String> abbMonList = Arrays.asList("proid", "proName", "targetDate", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
                Set<String> keySet = pro.keySet();
                for (String str : keySet) {
                    if (!str.equals("proid") && !str.equals("proName") && !str.equals("targetDate")) {
                        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(pro.get(str)));
                        if (jsonArray.size() == 1) {
                            List<Map<String, Object>> otherList = new ArrayList<>();
                            JSONObject json = JSONObject.parseObject(jsonArray.get(0).toString());
                            String monDataType = json.getString("monDataType");
                            String monDataTypeOther = "";
                            if (monDataType.equals("0")) {
                                monDataTypeOther = "1";
                            } else {
                                monDataTypeOther = "0";
                            }
                            for (int i = 1; i <= 5; i++) {
                                Map<String, Object> wekMap = new HashMap<>();
                                wekMap.put("wekDataId", "");
                                wekMap.put("wekData", "");
                                wekMap.put("week", "wk" + i);
                                wekMap.put("wekDataType", monDataTypeOther);
                                otherList.add(wekMap);
                            }
                            Map<String, Object> monMap = new LinkedHashMap<>();
                            monMap.put("monDataId", "");
                            monMap.put("monData", "");
                            monMap.put("monDataType", monDataTypeOther);
                            monMap.put("month", "");
                            monMap.put("wekList", otherList);
                            jsonArray.add(monMap);
                            pro.put(str, jsonArray);
                        }
                    }
                    abbMonList = abbMonList.stream().filter(a -> !a.equals(str)).collect(Collectors.toList());
                }
                for (String key : abbMonList) {
                    List<Map<String, Object>> mList = new ArrayList<>();
                    for (int a = 0; a <= 1; a++) {
                        List<Map<String, Object>> wekList = new ArrayList<>();
                        for (int i = 1; i <= 5; i++) {
                            Map<String, Object> wekMap = new HashMap<>();
                            wekMap.put("wekDataId", "");
                            wekMap.put("wekData", "");
                            wekMap.put("week", "wk" + i);
                            wekMap.put("wekDataType", a);
                            wekList.add(wekMap);
                        }
                        Map<String, Object> monMap = new LinkedHashMap<>();
                        monMap.put("monDataId", "");
                        monMap.put("monData", "");
                        monMap.put("monDataType", a);
                        monMap.put("month", "");
                        monMap.put("wekList", wekList);
                        mList.add(monMap);
                    }
                    pro.put(key, mList);
                }
            }
        }
        return result;
    }

    public void processProduct(List<Product> list) {
        for (Product data : list) {
            if (StringUtils.isNotBlank(data.getProName())) {
                if (data.getProid() != null) {
                    Boolean flag = false;
                    Product product = new Product();
                    product.setProid(data.getProid());
                    product = productMapper.findProductByParam(product);
                    if (!data.getProName().equals(product.getProName())) {
                        product = new Product();
                        product.setProName(data.getProName());
                        product = productMapper.findProductByParam(product);
                        if (product == null) {
                            flag = true;
                            product = new Product();
                            product.setProid(data.getProid());
                            product.setProName(data.getProName());
                        }
                    } else {
                        if (StringUtils.isNotBlank(data.getTargetDate()) && !product.getTargetDate().equals(data.getTargetDate())) {
                            flag = true;
                            product.setTargetDate(data.getTargetDate());
                        }
                    }
                    if (flag) {
                        productMapper.updateProduct(product);
                    }
                } else {
                        Product pro = new Product();
                        pro.setProName(data.getProName());
                        pro = productMapper.findProductByParam(pro);
                        if (pro == null) {
                            productMapper.createProduct(data);
                            Long result=productMapper.findUserByParam(data);
                            if (result==null) {
                                productMapper.createProAndUser(data);
                            }
                        }
                }
                if (!data.getMonList().isEmpty()) {
                    for (JSONObject jsonObject : data.getMonList()) {
                        String monData = jsonObject.getString("monData");
                        monData = monData.equals("0.00") ? "" : monData;
                        Integer monDataType = jsonObject.getInteger("monDataType");
                        String month = jsonObject.getString("month");
                        Long monDataId = jsonObject.getLong("monDataId");
                        if (StringUtils.isNotBlank(monData)) {
                            Product product = new Product();
                            if (monDataId != null) {
                                product.setMonDataId(monDataId);
                                product = productMapper.findMonDataByParam(product);
                                if (!product.getMonData().equals(monData)) {
                                    if (StringUtils.isNotBlank(month) && StringUtils.isNotBlank(product.getMonth())
                                            && month.equals(product.getMonth()) && monDataType != null
                                            && product.getMonDataType() != null && monDataType == product.getMonDataType()) {
                                        product.setMonData(monData);
                                        productMapper.updateMonData(product);
                                    }
                                }
                            } else {
                                product.setMonth(month);
                                product.setMonDataType(monDataType);
                                product.setProid(data.getProid());
                                product = productMapper.findMonDataByParam(product);
                                if (product == null) {
                                    data.setMonth(month);
                                    data.setMonDataType(monDataType);
                                    data.setMonData(monData);
                                    productMapper.createMonData(data);
                                }
                            }
                        }
                        JSONArray wekDataArray = jsonObject.getJSONArray("wekList");
                        for (Object obj : wekDataArray) {
                            JSONObject json = JSONObject.parseObject(obj.toString());
                            String wekData = json.getString("wekData");
                            Integer wekDataType = json.getInteger("wekDataType");
                            String week = json.getString("week");
                            if (StringUtils.isNotBlank(wekData)) {
                                Long monId = data.getMonDataId() != null ? data.getMonDataId() : monDataId;
                                if (monId != null && StringUtils.isNotBlank(week) && wekDataType != null
                                        && monDataType != null && wekDataType == monDataType) {
                                    Product p = new Product();
                                    p.setMonDataId(monId);
                                    p.setWekDataType(wekDataType);
                                    p.setWeek(week);
                                    p = productMapper.findWekDataByParam(p);
                                    if (p == null) {
                                        data.setWekData(wekData);
                                        data.setWeek(week);
                                        data.setWekDataType(wekDataType);
                                        data.setMonDataId(monId);
                                        productMapper.createWekData(data);
                                    } else {
                                        p.setWekData(wekData);
                                        productMapper.updateWekData(p);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void delProduct(List<Long> proidList) {
        productMapper.delProduct(proidList);
    }

}
