package com.zxf.dao;

import com.zxf.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ProductCategoryMapperTest {

    @Autowired
    ProductCategoryMapper productCategoryMapper;


    @Test
    public void deleteByPrimaryKey() {
    }

    //测试插入
    @Test
    public void insert() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setShopId(7L);
        productCategory.setPriority(1);
        productCategory.setProductCategoryName("热饮");
        productCategory.setCreateTime(new Date());
        int insert = productCategoryMapper.insert(productCategory);
        assertEquals(1, insert);
    }

    /**
     * 测试选择性插入
     * */
    @Test
    public void insertSelective() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setShopId(7L);
        productCategory.setPriority(1);
        productCategory.setProductCategoryName("冷饮");
        productCategory.setCreateTime(new Date());
        int insert = productCategoryMapper.insertSelective(productCategory);
        assertEquals(1, insert);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void queryProductCategoryList() {
        ProductCategory productCategoryCondition = new ProductCategory();
        productCategoryCondition.setShopId(7L);
        List<ProductCategory> productCategoryList = productCategoryMapper.queryProductCategoryList(productCategoryCondition, 0, 3);
        assertEquals(2, productCategoryList.size());
    }

    /**
     * 测试批量插入
     * */
    @Test
    public void batchInsertProductCategory() {
        List<ProductCategory> productCategoryList = new ArrayList<>();
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setShopId(7L);
        productCategory1.setCreateTime(new Date());
        productCategory1.setProductCategoryName("中餐店");
        productCategory1.setPriority(3);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setShopId(8L);
        productCategory2.setCreateTime(new Date());
        productCategory2.setProductCategoryName("川菜");
        productCategory2.setPriority(5);

        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);

        //这里傻叉了 没有传入正确的参数就进行插入，结果总是出错
        int i = productCategoryMapper.batchInsertProductCategory(productCategoryList);
        System.out.println(i);
    }
}