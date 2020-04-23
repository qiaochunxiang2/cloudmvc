package com.sk.cloudmvc.service;

import com.sk.cloudmvc.until.QiNiuUploadUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author qiaochunxiang
 * @date 2020/4/20 20:30
 */
@Service
public class QiNiuService {

    @Autowired
    private QiNiuUploadUntil qiNiuUploadUntil;

    /**
     * markdown上传图片
     *
     * @param file markdown  图片
     * @return java.lang.String
     * @author qiaochunxiang
     * @date 20:34 2020/4/20
     **/
    public String uploadPhoto(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            String newKey = UUID.randomUUID().toString().replace("-", "");
            boolean upload = qiNiuUploadUntil.upload(inputStream, newKey);
            if (upload) {
                return qiNiuUploadUntil.getDominName() + "/" + newKey;
            }
        } catch (IOException e) {
            throw new RuntimeException(e.toString(), e);
        }
        return null;
    }

}
