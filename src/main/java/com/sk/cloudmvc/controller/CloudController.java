package com.sk.cloudmvc.controller;

import com.sk.cloudmvc.model.Cloud;
import com.sk.cloudmvc.service.CloudService;
import com.sk.cloudmvc.until.CommonResult;
import com.sk.cloudmvc.until.State;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("apply")
    @ApiOperation(value = "申请服务器", notes = "申请服务器")
    public CommonResult applyCloud(@RequestBody Cloud cloud) {
        CommonResult result = new CommonResult();
        try {
            boolean addResult = cloudService.addCloud(cloud);
            result.setData(addResult);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("shutdown")
    @ApiOperation(value = "服务器关机", notes = "服务器关机")
    public CommonResult shutdown(@RequestBody Cloud cloud) {
        CommonResult result = new CommonResult();
        try {
            State shutdownResult = cloudService.shutdown(cloud);
            if (shutdownResult == State.SUCCESS) {
                result.setData(true);
            } else {
                result.setData(false);
            }
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("restart")
    @ApiOperation(value = "服务器重启", notes = "服务器关机")
    public CommonResult restart(@RequestBody Cloud cloud) {
        CommonResult result = new CommonResult();
        try {
            boolean restart = cloudService.restart(cloud);
            result.setData(restart);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @DeleteMapping("delete")
    @ApiOperation(value = "服务器卸载", notes = "服务器卸载")
    public CommonResult delete(@RequestParam(name = "id", required = false) String id) {
        CommonResult result = new CommonResult();
        try {
            boolean delete = cloudService.delete(id);
            result.setData(delete);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }
}
