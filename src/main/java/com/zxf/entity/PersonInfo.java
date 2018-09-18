package com.zxf.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zxf
 * @date 2018/9/17 14:24
 */
@Data
public class PersonInfo {
    private Long userId;

    private String name;

    private String profileImg;

    private String email;

    private String gender;

    private Integer enableStatus;

    //1. 顾客，2. 店家、3. 管理员
    private Integer userType;

    private Date createTime;

    private Date laseEditTime;
}
