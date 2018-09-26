package com.zxf.dao;

import com.zxf.combineEntity.ShopCombine;
import com.zxf.entity.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(Shop record);

    //插入信息，注意这里才是
    int insertSelective(Shop record);

    //根据主键查询
    Shop selectByPrimaryKey(Integer shopId);

    //更新店铺信息
    //id，ownerId, createTime是不能修改的，在业务层面实现
    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    //新增根据主键查询符合类型的数据
    ShopCombine queryById(Integer shopId);

    /**
     * 分页查询店铺
     *      可能有的查询条件：
     *         店铺名称、店铺状态、店铺类别、区域id、ownerId
     * @param shopCondition
     * @param rowIndex 从第几行开始取
     * @param pageSize 返回的条数
     * */
    List<ShopCombine> queryShopList(@Param("shopCondition")Shop shopCondition, @Param("rowIndex")int rowIndex,
                             @Param("pageSize")int pageSize);

    //获取shop的总数
    int queryShopCount(@Param("shopCondition")Shop shopCondition);
}