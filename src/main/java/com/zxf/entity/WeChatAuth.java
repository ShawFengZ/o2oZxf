package com.zxf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/17 14:36
 */
@Data
public class WeChatAuth {

    private Long wechatAuthId;

    private String openId;

    private Date createTime;

    //联表 userId
    private PersonInfo personInfo;
}
