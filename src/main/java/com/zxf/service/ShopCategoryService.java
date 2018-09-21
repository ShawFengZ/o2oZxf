package com.zxf.service;

import com.zxf.entity.ShopCategory;

import java.util.List;

/**
 * @author zxf
 * @date 2018/9/21 9:25
 */
public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(Integer parentId);
}
