package com.zxf.exceptions;

/**
 * @author zxf
 * @date 2018/9/19 10:47
 */
//对RuntimeException进行了封装
public class ShopOperationExceptions extends RuntimeException {

    private static final Long serialVersionUID = 2361446884822298905L;

    public ShopOperationExceptions(String msg){
        super(msg);
    }
}
