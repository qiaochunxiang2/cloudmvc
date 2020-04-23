package com.sk.cloudmvc.model;

import lombok.Data;

/**
 * @author qiaochunxiang
 * @date 2020/4/23 16:57
 */
@Data
public class Company {

    /**
     * 公司id
     */
    private String id;

    /**
     * 公司名字
     */
    private String name;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 公司电话
     */
    private String phone;
}
