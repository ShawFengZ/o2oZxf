package com.zxf.dao;

import com.zxf.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ShopCategoryMapperTest {

    @Autowired
    ShopCategoryMapper shopCategoryMapper;
    @Test
    public void queryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryMapper.queryShopCategory(null);
        assertEquals(2, shopCategoryList.size());
    }

    @Test
    public void insert() {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryName("饮品店");
        shopCategory.setShopCategoryDesc("这是专卖饮品的店铺");
        shopCategory.setShopCategoryImg("D:");
        shopCategory.setPriority(1);
        shopCategory.setCreateTime(new Date());
        shopCategory.setLastEditTime(new Date());
        shopCategory.setParentId(2);

        shopCategoryMapper.insert(shopCategory);
    }

    @Test
    public void insertSelective() {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryName("餐饮店");
        shopCategory.setShopCategoryDesc("这是专卖饮品的店铺");
        shopCategory.setPriority(1);
        shopCategory.setCreateTime(new Date());
        shopCategory.setLastEditTime(new Date());

        shopCategoryMapper.insertSelective(shopCategory);
    }

    @Test
    public void selectByPrimaryKey() {
    }
}