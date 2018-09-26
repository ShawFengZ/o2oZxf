package com.zxf.exceptions;

/**
 * @author zxf
 * @date 2018/9/19 10:47
 */
//对RuntimeException进行了封装
public class ProductCategoryOperationExceptions extends RuntimeException {

    private static final Long serialVersionUID = 1182563719599527969L;

    public ProductCategoryOperationExceptions(String msg){
        super(msg);
    }
}
