package com.sk.cloudmvc.model;

import lombok.Data;

import java.util.Date;

/**
 * @author qiaochunxiang
 * @date 2020/4/19 21:09
 */
@Data
public class Blog {
    /**
     * 博客id
     */
    private String id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客内容
     */
    private String content;


    /**
     * 用户资料
     */
    private UserInformation userInformation;

    /**
     * 发布时间
     */
    private Date publishDate;
}
