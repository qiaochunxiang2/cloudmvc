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
     * ip地址
     */
    private String ip;

    /**
     * 运行状态
     */
    private Integer state;

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
