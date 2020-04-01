package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.model.User;
import com.sk.cloudmvc.service.UserService;
import com.sk.cloudmvc.until.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author qiaochunxiang
 * @date 2020/3/24 14:55
 */
@Api(value = "/user")
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public CommonResult login(@RequestBody Map<String, Object> jsondata) {
        CommonResult result = new CommonResult();
        try {
            User user = userService.login(jsondata);
            if (user != null) {
                result.setData(user);
            } else {
                result.setData(false);
            }
        } catch (Exception e) {
            result.setState(500);
            result.setData(e);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("/changePassword")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public CommonResult changePassword(@RequestBody Map<String, Object> jsonData) {
        CommonResult result = new CommonResult();
        try {
            long number = userService.changePassword(jsonData);
            if (number <= 0) {
                result.setData(false);
            } else {
                result.setData(true);
            }
        } catch (Exception e) {
            result.setState(500);
            result.setData(e);
            result.setMsg("服务器错误");
            LOGGER.error(e.toString(), e);
        }
        return result;
    }
}
