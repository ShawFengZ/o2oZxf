package com.zxf.service.impl;

import com.zxf.combineEntity.ShopCombine;
import com.zxf.dao.ShopMapper;
import com.zxf.dto.ShopCombineExecution;
import com.zxf.entity.Shop;
import com.zxf.service.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@ContextConfiguration({"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShopServiceImplTest {

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    ShopService shopService;

    @Test
    public void getByShopId() {
        ShopCombine shopCombine = shopMapper.queryById(7);
        System.out.println(shopCombine);
    }



    @Test
    public void modeifyShop() {

    }

    //测试
    @Test
    public void getShopList() {
        Shop shopCondition = new Shop();
        shopCondition.setOwnerId(1);
        ShopCombineExecution shopList = shopService.getShopList(shopCondition, 0, 3);
        for (ShopCombine s: shopList.getShopList()) {
            System.out.println(s.toString());
        }
    }
}