package com.sk.cloudmvc.dao;

import com.sk.cloudmvc.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author qiaochunxiang
 * @date 2020/3/24 14:57
 */
@Repository
public interface UserMapper {
    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return com.sk.cloudmvc.model.User
     * @author qiaochunxiang
     * @date 15:17 2020/3/24
     **/
    User login(@Param("username") String username, @Param("password") String password);

    /**
     * 修改密码
     *
     * @param jsondata json数据
     * @return long
     * @author qiaochunxiang
     * @date 14:48 2020/4/1
     **/
    long changePassword(Map<String, Object> jsondata);
}
