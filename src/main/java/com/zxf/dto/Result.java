package com.zxf.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zxf
 * @date 2018/9/26 14:35
 */
@Data
public class Result<T> {

    private boolean success;//是否成功

    private T data;//成功时返回的数据

    private String errMsg;

    private int errorCode;

    public Result() {
    }

    //成功时的构造器
    public Result(boolean success, T data){
        this.success = success;
        this.data = data;
    }

    //错误时的构造器
    public Result(boolean success, int errorCode, String errMsg) {
        this.success = success;
        this.errMsg = errMsg;
        this.errorCode = errorCode;
    }
}
