package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.model.Cloud;
import com.sk.cloudmvc.service.CloudService;
import com.sk.cloudmvc.until.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qiaochunxiang
 * @date 2020/4/8 14:43
 */
@Api("cloud")
@RequestMapping("cloud")
@RestController
public class CloudController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudController.class);

    @Autowired
    private CloudService cloudService;

    @GetMapping("findAll")
    @ApiOperation(value = "获取所有的服务器", notes = "获取所有的服务器")
    public CommonResult findAll(String userId) {
        CommonResult result = new CommonResult();
        try {
            List<Cloud> clouds = cloudService.findAll(userId);
            result.setData(clouds);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

}
