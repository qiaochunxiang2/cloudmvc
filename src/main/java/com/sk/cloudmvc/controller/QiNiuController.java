package com.sk.cloudmvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.sk.cloudmvc.service.QiNiuService;
import com.sk.cloudmvc.until.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qiaochunxiang
 * @date 2020/4/20 17:20
 */
@RestController
@RequestMapping("/qiniu")
@Api("qiniu")
public class QiNiuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiNiuController.class);


    @Autowired
    private QiNiuService qiNiuService;

    @RequestMapping("/upload")
    @ApiOperation(value = "markdown上传图片", notes = "，markdown上传图片")
    public JSONObject markdown(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        String url = qiNiuService.uploadPhoto(file);
        jsonObject.put("success", 1);
        jsonObject.put("message", "上传成功");
        jsonObject.put("url", url);
        return jsonObject;
    }

    @PostMapping("/updatePhoto")
    @ApiOperation(value = "更改头像覆盖上传并刷新", notes = "更改头像覆盖上传并刷新")
    public CommonResult uploadAndRefresh(MultipartFile file, String id, String key) {
        CommonResult result = new CommonResult();
        try {
            Object uploadResult = qiNiuService.updatePhoto(file, id, key);
            result.setData(uploadResult);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            LOGGER.error(e.toString(), e);
        }
        return result;
    }

    @PostMapping("uploadPhoto")
    @ApiOperation(value = "上传新文件然后删除旧文件", notes = "上传新文件然后删除旧文件")
    public CommonResult uploadAndDelete(MultipartFile file, String id, String oldKey) {
        CommonResult result = new CommonResult();
        try {
            String newKey = qiNiuService.uploadAndDeletePhoto(file, id, oldKey);
            result.setData(newKey);
        } catch (Exception e) {
            result.setState(500);
            result.setMsg("服务器错误");
            result.setData(false);
            LOGGER.error(e.toString(), e);
        }
        return result;
    }
}
