package com.hanergy.reportForms.mapper.product;

import com.hanergy.reportForms.mapper.entity.Product;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {

    public Product findProductByParam(Product param);

    public Long findUserByParam(Product param);
    public Product findMonDataByParam(Product param);
    public Product findWekDataById(Long wekDataId);
    public List<Map<String,String>> productList(Product product);

    public Product findWekDataByParam(Product product);

    public Integer createProduct(Product product);

    public Integer createWekData(Product product);

    public Integer createMonData(Product product);

    public Integer createProAndUser(Product product);

    public Integer updateProduct(Product product);

    public Integer updateWekData(Product product);

    public Integer updateMonData(Product product);

    public void delProduct(List<Long> list);
}
