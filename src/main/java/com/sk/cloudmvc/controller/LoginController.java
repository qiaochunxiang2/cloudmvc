package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.service.LoginService;
import com.sk.cloudmvc.until.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaochunxiang
 * @date 2020/3/24 14:55
 */
@Api(value = "/user")
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public CommonResult login(String username, String password) {
        CommonResult result = new CommonResult();
        try {
            result = loginService.login(username, password);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("账号错误");
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

}
