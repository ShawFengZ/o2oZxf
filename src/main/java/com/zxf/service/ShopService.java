package com.zxf.service;

import com.zxf.dto.ShopExecution;
import com.zxf.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author zxf
 * @date 2018/9/19 10:16
 */
public interface ShopService {
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);
}
