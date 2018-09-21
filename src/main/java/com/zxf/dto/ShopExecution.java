package com.zxf.dto;

/**
 * @author zxf
 * @date 2018/9/19 9:51
 */

import com.zxf.entity.Shop;
import com.zxf.enums.ShopStateEnum;
import lombok.Data;

import java.util.List;

/**
 *  店铺注册的返回值
 * */
@Data
public class ShopExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //店铺数量，有的时候操作
    private int count;

    //操作的Shop(增删改的时候用到)
    private Shop shop;

    //shop列表，查询时的时候用到
    private List<Shop> shopList;

    public ShopExecution() {
    }

    //针对失败的清空的构造器
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //针对成功的清空的构造器
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
        this.count = 1;
    }

    //查找成功的构造器
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
        this.count = shopList.size();
    }
}
