package com.zxf.entity;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/18 9:31
 */
@lombok.Data
public class ProductImg {

    private Long productImgId;

    private String imgAddr;

    private String imgDesc;

    private Date createTime;

    private Long productId;
}
