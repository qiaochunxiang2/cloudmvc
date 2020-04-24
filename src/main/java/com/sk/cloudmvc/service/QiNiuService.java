package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.UserInformationMapper;
import com.sk.cloudmvc.until.DeleteImgThread;
import com.sk.cloudmvc.until.QiNiuUploadUntil;
import com.sk.cloudmvc.until.UpdateInformationThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author qiaochunxiang
 * @date 2020/4/20 20:30
 */
@Service
public class QiNiuService {

    @Autowired
    private QiNiuUploadUntil qiNiuUploadUntil;

    private static ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(5);

    @Autowired
    private UserInformationMapper userInformationMapper;

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

    /**
     * 修改头像
     *
     * @param file 图片信息
     * @param id   用户名
     * @param key  头像位置
     * @return boolean
     * @author qiaochunxiang
     * @date 21:58 2020/4/3
     **/
    public Object updatePhoto(MultipartFile file, String id, String key) {
        try (InputStream inputStream = file.getInputStream()) {
            // 如果是默认头像就直接上传然后退出方法
            if ("default".equals(key)) {
                String newKey = UUID.randomUUID().toString().replace("-", "");
                boolean uploadResult = qiNiuUploadUntil.upload(inputStream, newKey);
                if (uploadResult) {
                    executor.execute(new UpdateInformationThread(id, newKey, userInformationMapper));
                    return newKey;
                }
                return false;
            }
            boolean uploadResult = qiNiuUploadUntil.upload(inputStream, key);
            // 如果上传成功就刷新缓存
            if (uploadResult) {
                String refreshUrl = qiNiuUploadUntil.getDominName() + key;
                return qiNiuUploadUntil.refreshUrls(refreshUrl);
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e.toString(), e);
        }
    }

    /**
     * 先上传新的头像，然后异步删除旧的图片
     *
     * @param file   文件
     * @param id     用户名
     * @param oldkey 旧图片位置
     * @return java.lang.String
     * @author qiaochunxiang
     * @date 17:38 2020/4/5
     **/
    public String uploadAndDeletePhoto(MultipartFile file, String id, String oldkey) {
        String newKey = UUID.randomUUID().toString().replace("-", "");
        try (InputStream inputStream = file.getInputStream()) {
            boolean uploadResult = qiNiuUploadUntil.upload(inputStream, newKey);
            if (uploadResult && !"default".equals(oldkey)) {
                executor.execute(new DeleteImgThread(oldkey, qiNiuUploadUntil));
                executor.execute(new UpdateInformationThread(id, newKey, userInformationMapper));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.toString(), e);
        }
        return newKey;
    }
}
