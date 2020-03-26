package com.sk.cloudmvc.model;

import lombok.Data;

/**
 * @author qiaochunxiang
 * @date 2020/3/26 16:09
 */
@Data
public class UserInformation {

    /**
     * id
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像地址
     */
    private String imageUrl;

    /**
     * 手机号
     */
    private String phone;
}