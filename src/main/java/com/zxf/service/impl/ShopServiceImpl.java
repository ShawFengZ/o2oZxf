package com.zxf.service.impl;

import com.zxf.combineEntity.ShopCombine;
import com.zxf.dao.ShopMapper;
import com.zxf.dto.ShopCombineExecution;
import com.zxf.dto.ShopExecution;
import com.zxf.entity.Shop;
import com.zxf.enums.ShopStateEnum;
import com.zxf.exceptions.ShopOperationExceptions;
import com.zxf.service.ShopService;
import com.zxf.util.ImageUtil;
import com.zxf.util.FileUtil;
import com.zxf.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.EventListener;
import java.util.List;

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

    /**
     * 根据店铺id获取店铺信息
     *
     * @param shopId
     */
    @Override
    public ShopCombine getByShopId(long shopId) {
        return shopMapper.queryById((int)shopId);
    }

    /**
     * 更新店铺信息包括对图片处理
     * 抛出异常会中断时间的处理
     *
     * @param shop
     * @param shopImg
     */
    @Override
    public ShopExecution modeifyShop(Shop shop, CommonsMultipartFile shopImg) throws ShopOperationExceptions {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                //1. 判断是否有需要处理的图片
                if (shopImg != null){
                    ShopCombine shopTemp = shopMapper.queryById(shop.getShopId().intValue());
                    //如果之前就有图片信息，则需要进行处理,删除
                    if (shopTemp.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(shopTemp.getShopImg());
                    }
                    //参数传递的不正确
                    addShopImg(shop, shopImg);
                }
                //2. 更新店铺信息
                shop.setLastEditTime(new Date());
                int effectNum = shopMapper.updateByPrimaryKeySelective(shop);
                if (effectNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopMapper.selectByPrimaryKey(shop.getShopId().intValue());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationExceptions("modifyShop error:" + e.getMessage());
            }
        }
    }

    /**
     * 获取项目列表的方法
     * */
    @Override
    public ShopCombineExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        //1. 将pageIndex转换为rowIndex
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<ShopCombine> shopCombineList = shopMapper.queryShopList(shopCondition, pageIndex, pageSize);
        int count = shopMapper.queryShopCount(shopCondition);
        ShopCombineExecution shopCombineExecution = new ShopCombineExecution();
        if (shopCombineList != null) {
            shopCombineExecution.setShopList(shopCombineList);
            shopCombineExecution.setCount(count);
        } else {
            //查询失败
            shopCombineExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return shopCombineExecution;
    }
}
