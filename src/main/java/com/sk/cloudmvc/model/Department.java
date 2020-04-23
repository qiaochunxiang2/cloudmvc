package com.sk.cloudmvc.model;

import lombok.Data;

/**
 * @author qiaochunxiang
 * @date 2020/4/23 17:11
 */
@Data
public class Department {

    /**
     * 部门id
     */
    private String id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 所属公司
     */
    private Company company;
}
