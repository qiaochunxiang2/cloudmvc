package com.sk.cloudmvc.model;

import lombok.Data;

import java.util.Date;

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

    /**
     * QQ
     */
    private String qq;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 个人简介
     */
    private String note;
}
