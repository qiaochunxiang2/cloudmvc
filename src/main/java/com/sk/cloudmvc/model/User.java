package com.sk.cloudmvc.model;

import lombok.Data;

/**
 * @author qiaochunxiang
 * @date 2020/3/24 15:13
 */
@Data
public class User {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
