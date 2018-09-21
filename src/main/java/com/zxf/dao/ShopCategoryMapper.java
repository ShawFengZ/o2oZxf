package com.zxf.dao;

import com.zxf.entity.ShopCategory;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ShopCategoryMapper {

    /**
     * 查出所有的店铺类别
     * */
    List<ShopCategory> queryShopCategory(Integer parentId);

    int deleteByPrimaryKey(Integer shopCategoryId);

    int insert(ShopCategory record);

    int insertSelective(ShopCategory record);

    ShopCategory selectByPrimaryKey(Integer shopCategoryId);

    int updateByPrimaryKeySelective(ShopCategory record);

    int updateByPrimaryKey(ShopCategory record);
}