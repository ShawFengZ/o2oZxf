package com.zxf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/17 15:19
 */
@Data
public class Shop {
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

    //和用户表关联
    private Integer ownerId;

    //和ShopCategory关联
    private Integer shopCategoryId;
}
