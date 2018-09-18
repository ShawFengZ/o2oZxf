package com.zxf.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/17 14:05
 */
@Data
public class Area {
    //id
    private Integer areaId;

    //名称
    private String areaName;

    //权重
    private Integer priority;

    //创建时间
    private Date createTime;

    //更新时间
    private Date lastEditTime;
}
