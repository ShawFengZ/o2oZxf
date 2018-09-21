package com.zxf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/17 14:38
 */
@Data
public class LocalAuth {

    private Long localAuthId;

    private String username;

    private String password;

    private Date createTime;

    private Date lastEditTime;

    //和personInfo关联
    private Integer userId;
}
