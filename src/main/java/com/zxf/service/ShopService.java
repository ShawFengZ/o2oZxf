package com.zxf.service;

import com.zxf.combineEntity.ShopCombine;
import com.zxf.dto.ShopCombineExecution;
import com.zxf.dto.ShopExecution;
import com.zxf.entity.Shop;
import com.zxf.exceptions.ShopOperationExceptions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;

/**
 * @author zxf
 * @date 2018/9/19 10:16
 */
public interface ShopService {
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg);

    /**
     * 根据店铺id获取店铺信息
     * */
    ShopCombine getByShopId(long shopId);

    /**
     *更新店铺信息包括对图片处理
     * 抛出异常会中断时间的处理
     * */
    ShopExecution modeifyShop(Shop shop, CommonsMultipartFile file) throws ShopOperationExceptions;

    //获取店铺列表信息
    public ShopCombineExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
