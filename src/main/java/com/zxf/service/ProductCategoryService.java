package com.zxf.service;

import com.zxf.dto.ProductCategoryExecution;
import com.zxf.entity.ProductCategory;

import java.util.List;

/**
 * @author zxf
 * @date 2018/9/26 13:32
 */
public interface ProductCategoryService {
    /**
     * 列表展示
     * */
    List<ProductCategory> getProductCategoryList(ProductCategory productCategoryCondition, int pageIndex, int pageSize);

    /**
     * 批量插入的方法
     * */
    ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategoryList);

    /**
     * 按照主键删除
     * */
    ProductCategoryExecution deleteProductCategory(Long productCategoryId);
}
