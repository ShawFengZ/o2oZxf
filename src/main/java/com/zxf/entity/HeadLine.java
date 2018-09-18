package com.zxf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/17 14:59
 */
//头条
@Data
public class HeadLine {

    private Long lineId;

    private String lineName;

    //头条链接
    private String lineLink;

    private String lineImg;

    //优先级
    private Integer priority;

    //0. 不可用， 1. 可用
    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;

}
