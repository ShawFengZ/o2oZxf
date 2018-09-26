package com.zxf.dao;

import com.zxf.entity.ProductCategory;
import com.zxf.exceptions.ProductCategoryOperationExceptions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryMapper {

    /**
     * 列表展示,查询条件: 类别名称、优先级、shopId
     * */
    List<ProductCategory> queryProductCategoryList(@Param("productCategoryCondition") ProductCategory productCategoryCondition
            , @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 批量新增商品类别
     * */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationExceptions;

    int deleteByPrimaryKey(Integer productCategoryId);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer productCategoryId);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);
}