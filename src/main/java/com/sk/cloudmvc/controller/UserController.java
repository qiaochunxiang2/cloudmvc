package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.model.User;
import com.sk.cloudmvc.model.UserInformation;
import com.sk.cloudmvc.service.UserService;
import com.sk.cloudmvc.until.CommonResult;
import com.sk.cloudmvc.until.QiNiuUploadUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private QiNiuUploadUntil qiNiuUploadUntil;

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

    @PostMapping("/register")
    @ApiOperation(value = "注册功能", notes = "注册功能")
    public CommonResult register(@RequestBody Map<String, String> jsondata) {
        CommonResult result = new CommonResult();
        try {
            userService.register(jsondata);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("用户名已存在");
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("/updatePhoto")
    @ApiOperation(value = "更改头像覆盖上传并刷新", notes = "更改头像覆盖上传并刷新")
    public CommonResult uploadAndRefresh(MultipartFile file, String id, String key) {
        CommonResult result = new CommonResult();
        try {
            Object uploadResult = userService.updatePhoto(file, id, key);
            result.setData(uploadResult);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            LOGGER.error(e.toString(), e);
        }
        System.out.println(result);
        return result;
    }

    @PostMapping("uploadPhoto")
    @ApiOperation(value = "上传新文件然后删除旧文件", notes = "上传新文件然后删除旧文件")
    public CommonResult uploadAndDelete(MultipartFile file, String id, String oldKey) {
        CommonResult result = new CommonResult();
        try {
            String newKey = userService.uploadAndDeletePhoto(file, id, oldKey);
            result.setData(newKey);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
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
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("/test")
    public String test() {
        System.out.println(qiNiuUploadUntil);
        return "success";
    }
}
