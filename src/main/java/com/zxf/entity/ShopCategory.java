package com.zxf.entity;

import lombok.Data;

import java.io.InputStream;
import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/17 15:08
 */
@Data
public class ShopCategory {

    private Long shopCategoryId;

    private String shopCategoryName;

    private String shopCategoryDesc;

    private String shopCategoryImg;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    //parentId
    private Integer parentId;
}
