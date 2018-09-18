package com.zxf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/18 9:23
 */
@Data
public class ProductCategory {

    private Long productCategoryId;

    private Long shopId;

    private String productCategoryName;

    private Integer priority;

    private Date createTime;

}
