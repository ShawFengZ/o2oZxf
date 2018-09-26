package com.zxf.service.impl;

import com.zxf.dao.ProductCategoryMapper;
import com.zxf.dto.ProductCategoryExecution;
import com.zxf.entity.ProductCategory;
import com.zxf.enums.ProductCategoryStateEnum;
import com.zxf.exceptions.ProductCategoryOperationExceptions;
import com.zxf.service.ProductCategoryService;
import com.zxf.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zxf
 * @date 2018/9/26 13:35
 */
@Service
public class ProdcutCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    /**
     * 列表展示
     *
     * @param productCategoryCondition
     * @param pageIndex
     * @param pageSize
     */
    @Override
    public List<ProductCategory> getProductCategoryList(ProductCategory productCategoryCondition, int pageIndex, int pageSize) {
        //将pageIndex转换为rowIndex
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<ProductCategory> productCategoryList = productCategoryMapper.queryProductCategoryList(
                productCategoryCondition, rowIndex, 100);
        if (productCategoryList.size()!=0) {
            return productCategoryList;
        } else {
            return null;
        }
    }

    /**
     * 批量插入的方法
     *
     * @param productCategoryList
     */
    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategoryList(List<ProductCategory> productCategoryList)
        throws ProductCategoryOperationExceptions {
        if (productCategoryList!=null && productCategoryList.size()>0) {
            try {
                int effectedNum = productCategoryMapper.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationExceptions("商品类别添加失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationExceptions("batchAddProductCategory error:" + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    /**
     * 按照主键删除
     *
     * @param productCategoryId
     */
    @Override
    public ProductCategoryExecution deleteProductCategory(Long productCategoryId) {
        ProductCategoryExecution pe = new ProductCategoryExecution();
        if (productCategoryId != null) {
            try {
                int effectNum = productCategoryMapper.deleteByPrimaryKey(productCategoryId.intValue());
                if (effectNum <= 0) {
                    throw new ProductCategoryOperationExceptions("删除商品类别失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationExceptions("delete productCategory error:" + e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.INNER_ERROR);
        }
    }
}
