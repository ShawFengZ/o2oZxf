package com.zxf.entity;

import lombok.Data;

import java.net.Inet4Address;
import java.util.Date;
import java.util.List;

/**
 * @author zxf
 * @date 2018/9/18 9:39
 */
@Data
public class Product {

    private Long productId;

    private String productName;

    private String productDesc;

    private String imgAddr;

    private String normalPrice;

    private String promotionPrice;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    private Integer enableStatus;

    private List<ProductImg> productImgList;

    private ProductCategory productCategory;

    private Shop shop;
}
