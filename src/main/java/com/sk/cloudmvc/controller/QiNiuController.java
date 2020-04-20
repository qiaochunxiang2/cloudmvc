package com.sk.cloudmvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.sk.cloudmvc.service.QiNiuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
}
