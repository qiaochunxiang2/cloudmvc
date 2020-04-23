package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.BlogMapper;
import com.sk.cloudmvc.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author qiaochunxiang
 * @date 2020/4/19 21:26
 */
@Service
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    /**
     * 博客发布
     *
     * @param jsonData 博客内容
     * @return boolean
     * @author qiaochunxiang
     * @date 21:14 2020/4/19
     **/
    public boolean publish(Map<String, Object> jsonData) {
        String id = UUID.randomUUID().toString().replace("-", "");
        jsonData.put("id", id);
        String describe = (String) jsonData.get("content");
        if (describe.length() > 150) {
            describe = describe.replaceAll("[^0-9a-zA-Z\u4e00-\u9fa5.，,。？“”]+", "").substring(0, 150);
        }
        jsonData.put("describe", describe);
        return blogMapper.publish(jsonData);
    }

    /**
     * 查询所有博客
     *
     * @return java.util.List<com.sk.cloudmvc.model.Blog>
     * @author qiaochunxiang
     * @date 21:14 2020/4/19
     **/
    public List<Blog> findAll() {
        return blogMapper.findAll();
    }

    /**
     * 查询用户所有博客
     *
     * @param uid 用户id
     * @return java.util.List<com.sk.cloudmvc.model.Blog>
     * @author qiaochunxiang
     * @date 21:15 2020/4/19
     **/
    public List<Blog> personAll(String uid) {
        return blogMapper.personAll(uid);
    }
}
