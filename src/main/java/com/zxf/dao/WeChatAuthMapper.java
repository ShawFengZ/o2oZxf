package com.zxf.dao;

import com.zxf.entity.WeChatAuth;

public interface WeChatAuthMapper {
    int deleteByPrimaryKey(Integer wechatAuthId);

    int insert(WeChatAuth record);

    int insertSelective(WeChatAuth record);

    WeChatAuth selectByPrimaryKey(Integer wechatAuthId);

    int updateByPrimaryKeySelective(WeChatAuth record);

    int updateByPrimaryKey(WeChatAuth record);
}