package com.hanergy.reportForms.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hanergy.reportForms.mapper.entity.Product;
import com.hanergy.reportForms.mapper.product.ProductMapper;
import com.hanergy.reportForms.service.product.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    @PostMapping("/list")
    public JSONObject productList(@RequestBody Product product) {
        JSONObject result = new JSONObject();
        try {
            List<Map> list = productService.productList(product);
            result.put("code", "200");
            result.put("msg", "success");
            result.put("data", list);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "500");
            result.put("msg", "fail");
        }
        return result;
    }
/**
 *
 *
 *
 *
 [{
 "proid": null,
 "proName": "汉伞",
 "userId":18000030,
 "targetDate": "2019-06-30",
 "monList": [
 {
 "monDataId": null,
 "monData": "3.80",
 "monDataType": "0",
 "month": "2019-04-18",
 "wekList": [
 {
 "wekDataId": null,
 "wekData": "3.70",
 "week": "wk16",
 "wekDataType": "0"
 }
 ]
 },
 {
 "monDataId":null,
 "monData": "3.68",
 "monDataType": "1",
 "month": "2019-04-18",
 "wekList": [
 {
 "wekDataId": null,
 "wekData": "3.68",
 "week": "wk16",
 "wekDataType": "1"
 }
 ]
 }
 ]
 }]
 * */
    @PostMapping("/save")
    public JSONObject processProduct(@RequestBody List<Product>productList) {
        JSONObject result = new JSONObject();
        try {
            productService.processProduct(productList);
            result.put("code", "200");
            result.put("msg", "success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", "500");
            result.put("msg", "fail");
        }
        return result;
    }


    @PostMapping("/del")
    public JSONObject delProduct(@RequestBody List<Long>proidList){
        JSONObject result=new JSONObject();
        try {
            productService.delProduct(proidList);
            result.put("code", "200");
            result.put("msg", "success");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", "500");
            result.put("msg", "fail");
        }
        return result;
    }
}
