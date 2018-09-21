package com.zxf.dao;

import com.zxf.entity.Area;
import com.zxf.entity.PersonInfo;
import com.zxf.entity.Shop;
import com.zxf.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ShopMapperTest {

    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void insert() {
        Shop shop = new Shop();
        shop.setOwnerId(1);
        shop.setAreaId(1);
        shop.setShopCategoryId(1);
        shop.setShopAddr("闵行区");

        shop.setShopName("测试的店铺");
        shop.setShopDesc("Test");
        shop.setPhone("15310614291");
        shop.setShopImg("D:");
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        shop.setPriority(1);
        int insert = shopMapper.insertSelective(shop);
        assertEquals(1, insert);
    }

    @Test
    public void insertSelective() {

    }

    /**
     * 测试更新信息
     * */
    @Test
    public void updateByPrimaryKeySelective() {
        Shop shop = shopMapper.selectByPrimaryKey(7);
        shop.setCreateTime(new Date());
        shop.setShopCategoryId(1);
        shop.setShopName("奶茶店");
        shop.setShopDesc("这家奶茶店非常好吃");
        shop.setShopAddr("闵行区联航路");
        shopMapper.updateByPrimaryKeySelective(shop);
    }

    @Test
    public void updateByPrimaryKey() {
    }
}