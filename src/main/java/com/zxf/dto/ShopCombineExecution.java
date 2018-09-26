package com.zxf.dto;

import com.zxf.combineEntity.ShopCombine;
import com.zxf.entity.Shop;
import com.zxf.enums.ShopStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @author zxf
 * @date 2018/9/25 16:41
 * 店铺列表的返回值
 */
@Data
public class ShopCombineExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //店铺数量，有的时候操作
    private int count;

    private Shop shop;

    //shop列表，查询时的时候用到
    private List<ShopCombine> shopList;

    public ShopCombineExecution() {
    }

    //针对失败的清空的构造器
    public ShopCombineExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //针对成功的清空的构造器
    public ShopCombineExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
        this.count = 1;
    }

    //查找成功的构造器
    public ShopCombineExecution(ShopStateEnum stateEnum, List<ShopCombine> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
        this.count = shopList.size();
    }
}
