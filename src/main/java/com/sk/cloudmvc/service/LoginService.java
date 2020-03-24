package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.UserMapper;
import com.sk.cloudmvc.model.User;
import com.sk.cloudmvc.until.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiaochunxiang
 * @date 2020/3/24 15:19
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    public CommonResult login(String username, String password){
        CommonResult result = new CommonResult();
        User user =  userMapper.login(username, password);
        if (user==null){
            result.setData(false);
        } else {
            result.setData(true);
        }
        return result;
    }
}
