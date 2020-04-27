package com.sk.cloudmvc.dao;

import com.sk.cloudmvc.model.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author qiaochunxiang
 * @date 2020/4/19 21:12
 */
@Repository
public interface BlogMapper {

    /**
     * 博客发布
     *
     * @param jsonData 博客内容
     * @return boolean
     * @author qiaochunxiang
     * @date 21:14 2020/4/19
     **/
    boolean publish(Map<String, Object> jsonData);

    /**
     * 查询所有博客
     *
     * @return java.util.List<com.sk.cloudmvc.model.Blog>
     * @author qiaochunxiang
     * @date 21:14 2020/4/19
     **/
    List<Blog> findAll();

    /**
     * 查询用户所有博客
     *
     * @param uid 用户id
     * @return java.util.List<com.sk.cloudmvc.model.Blog>
     * @author qiaochunxiang
     * @date 21:15 2020/4/19
     **/
    List<Blog> personAll(String uid);

    /**
    *  删除博客
    *
    * @param id 博客id
    * @return boolean
    * @author qiaochunxiang
    * @date 9:40 2020/4/27
    **/
    boolean deleteBlog(String id);
}
