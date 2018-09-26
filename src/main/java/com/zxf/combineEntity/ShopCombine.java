package com.zxf.combineEntity;

import lombok.Data;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/25 8:57
 */
@Data
public class ShopCombine {

    private Long shopId;

    private String shopName;

    private String shopDesc;

    private String shopAddr;

    private String phone;

    private String shopImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    //-1. 不可用，0. 审核中，1.可用
    private Integer enableStatus;

    private String advice;

    //和Area表关联
    private Integer areaId;

    //新增的属性
    private String areaName;

    //和用户表关联，关联的是userId
    private Integer ownerId;

    //新增的属性
    private String userName;

    //和ShopCategory关联
    private Integer shopCategoryId;

    //新增的属性
    private String shopCategoryName;
}
