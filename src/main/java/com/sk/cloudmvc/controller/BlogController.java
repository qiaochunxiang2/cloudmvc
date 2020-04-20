package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.model.Blog;
import com.sk.cloudmvc.service.BlogService;
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
 * @date 2020/4/19 21:28
 */
@RestController
@Api("blog")
@RequestMapping("blog")
public class BlogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @GetMapping("/findAll")
    @ApiOperation(value = "查询所有博客", notes = "查询所有博客")
    public CommonResult findAll() {
        CommonResult result = new CommonResult();
        try {
            List<Blog> all = blogService.findAll();
            result.setData(all);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发布博客", notes = "发布博客")
    public CommonResult publish(@RequestBody Map<String, Object> jsonData) {
        CommonResult result = new CommonResult();
        try {
            boolean publishResult = blogService.publish(jsonData);
            result.setData(publishResult);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @GetMapping("/personAll")
    @ApiOperation(value = "查询个人所有博客", notes = "查询个人所有博客")
    public CommonResult personAll(String uid) {
        CommonResult result = new CommonResult();
        try {
            List<Blog> all = blogService.personAll(uid);
            result.setData(all);
        } catch (Exception e) {
            result.setState(500);
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }
}
