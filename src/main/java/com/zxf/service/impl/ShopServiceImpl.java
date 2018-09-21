package com.zxf.service.impl;

import com.zxf.dao.ShopMapper;
import com.zxf.dto.ShopExecution;
import com.zxf.entity.Shop;
import com.zxf.enums.ShopStateEnum;
import com.zxf.exceptions.ShopOperationExceptions;
import com.zxf.service.ShopService;
import com.zxf.util.ImageUtil;
import com.zxf.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/19 10:16
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
        //检查传入的参数是否合法
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //初始化参数
            shop.setEnableStatus(0);//未上架
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //插入
            int insertSelective = shopMapper.insertSelective(shop);
            if (insertSelective <= 0) {
                //事务才能终止并回滚
                throw new ShopOperationExceptions("店铺创建失败");
            } else {
                if (shopImg != null) {
                    try {
                        addShopImg(shop, shopImg);
                    } catch (Exception e) {
                        throw new ShopOperationExceptions("addShopImg error:" + e.getMessage());
                    }
                    //更新店铺的图片地址
                    int updateByPrimaryKeySelective = shopMapper.updateByPrimaryKeySelective(shop);
                    if (updateByPrimaryKeySelective <= 0) {
                        throw new ShopOperationExceptions("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationExceptions("addShopError:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg){
        //获取shop图片目录的相对值路径
        String dest = FileUtil.getShopImagePath(shop.getShopId());
        //存储图片返回相应的相对值路径
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgAddr);
    }
}
