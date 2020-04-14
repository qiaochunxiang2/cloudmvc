package com.sk.cloudmvc.model;

import lombok.Data;

import java.util.Date;

/**
 * @author qiaochunxiang
 * @date 2020/4/8 14:22
 */
@Data
public class Cloud {

    /**
     * 服务器id
     */
    private String id;

    /**
     * 服务器标签
     */
    private String label;

    /**
     * 服务器监控
     */
    private String control;

    /**
     * 服务器地址，比如华北华南
     */
    private String address;

    /**
     * 公有ip地址
     */
    private String ipAddressPublic;

    /**
     * 私有ip地址
     */
    private String ipAddressPrivate;

    /**
     * 运行状态
     */
    private Integer state;

    /**
     * 网络类型
     */
    private Integer netType;

    /**
     * 操作系统
     */
    private Integer system;


    /**
     * 内存大小
     */
    private Integer memory;

    /**
     * 硬盘大小
     */
    private Integer hardpan;

    /**
     * 内核大小
     */
    private Integer core;

    /**
     * 带宽大小
     */
    private Integer bandWith;

    /**
     * cpu类型
     */
    private Integer cpuType;

    /**
     * 申请日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 用户id
     */
    private String userId;
}
