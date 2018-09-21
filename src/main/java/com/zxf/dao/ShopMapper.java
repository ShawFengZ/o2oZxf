package com.zxf.dao;

import com.zxf.entity.Shop;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(Shop record);

    //插入信息，注意这里才是
    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer shopId);

    //更新店铺信息
    //id，ownerId, createTime是不能修改的，在业务层面实现
    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
}