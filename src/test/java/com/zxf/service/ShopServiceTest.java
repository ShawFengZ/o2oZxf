package com.zxf.service;

import com.zxf.entity.Shop;
import com.zxf.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void addShop() {
        Shop shop = new Shop();
        shop.setOwnerId(1);
        shop.setAreaId(1);
        shop.setShopCategoryId(1);
        shop.setShopAddr("闵行区");

        shop.setShopName("测试的店铺");
        shop.setShopDesc("Test");
        shop.setPhone("15310614291");
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        shop.setPriority(1);

        /*File shopImg = new File("C:/Users/97598/Desktop/image/timg.jpg");
        FileInputStream input = new FileInputStream(shopImg);
        shopService.addShop(shop, (CommonsMultipartFile)shopImg);*/
    }
}