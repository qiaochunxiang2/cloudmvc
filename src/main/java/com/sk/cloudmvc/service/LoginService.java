package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.UserMapper;
import com.sk.cloudmvc.model.User;
import com.sk.cloudmvc.until.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author qiaochunxiang
 * @date 2020/3/24 15:19
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    public User login(Map<String, Object> jsonData) {
        String username = jsonData.get("username").toString();
        String password = jsonData.get("password").toString();
        CommonResult result = new CommonResult();
        return userMapper.login(username, password);
    }
}
