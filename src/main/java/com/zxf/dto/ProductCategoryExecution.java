package com.zxf.dto;

import com.zxf.entity.Product;
import com.zxf.entity.ProductCategory;
import com.zxf.enums.ProductCategoryStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @author zxf
 * @date 2018/9/26 15:41
 */
@Data
public class ProductCategoryExecution {

    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //列表
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution() {
    }

    //失败的时候使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //操作成功的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

}
