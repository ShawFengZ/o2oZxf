package com.zxf.service.impl;

import com.zxf.dao.ShopCategoryMapper;
import com.zxf.entity.ShopCategory;
import com.zxf.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxf
 * @date 2018/9/21 9:26
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryMapper shopCategoryMapper;

    /**
     * 获取店铺类别信息
     * */
    @Override
    public List<ShopCategory> getShopCategoryList(Integer parentId) {
        return shopCategoryMapper.queryShopCategory(parentId);
    }
}
