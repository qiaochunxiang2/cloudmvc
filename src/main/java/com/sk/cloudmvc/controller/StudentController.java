package com.sk.cloudmvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiaochunxiang
 * @date 2020/3/21 10:57
 */
@RestController
@RequestMapping("/student")
@Api("test一下")
public class StudentController {


    @RequestMapping("/test")
    @ApiOperation(value = "测试", notes = "测试")
    public String test(){
        return "success2222";
    }
}
