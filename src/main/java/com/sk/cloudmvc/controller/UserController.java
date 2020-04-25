package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.model.User;
import com.sk.cloudmvc.model.UserInformation;
import com.sk.cloudmvc.service.UserService;
import com.sk.cloudmvc.until.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            result.setData(false);
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
            result.setData(false);
            result.setMsg("服务器错误");
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册功能", notes = "注册功能")
    public CommonResult register(@RequestBody User user) {
        CommonResult result = new CommonResult();
        try {
            userService.register(user);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            result.setMsg("用户名已存在");
            LOGGER.error(e.toString(), e);
        }
        return result;
    }


    @PostMapping("/updateInformation")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public CommonResult updateInformation(@RequestBody UserInformation information) {
        CommonResult result = new CommonResult();
        try {
            userService.updateInformation(information);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    public CommonResult findAll(@RequestParam(name = "cId", required = false) String cId,
                                @RequestParam(name = "dId", required = false) String dId) {
        CommonResult result = new CommonResult();
        try {
            List<User> all = userService.findAll(cId, dId);
            result.setData(all);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @DeleteMapping("/deleteUser")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public CommonResult delete(String id) {
        CommonResult result = new CommonResult();
        try {
            boolean deleteResult = userService.deleteUser(id);
            result.setData(deleteResult);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }
}
