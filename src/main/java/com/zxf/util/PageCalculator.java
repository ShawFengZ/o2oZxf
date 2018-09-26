package com.zxf.util;

/**
 * @author zxf
 * @date 2018/9/25 16:51
 */
public class PageCalculator {
    //转换工具
    public static int calculateRowIndex(int pageIndex, int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
