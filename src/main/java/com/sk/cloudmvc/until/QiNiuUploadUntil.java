package com.sk.cloudmvc.until;

import com.qiniu.storage.UploadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Region;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.InputStream;

/**
 * 七牛云文件上传工具类
 *
 * @author qiaochunxiang
 * @date 2020/4/2 20:01
 */
@Configuration
@SuppressWarnings("unused")
public class QiNiuUploadUntil {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiNiuUploadUntil.class);

    /**
     * 账号AK
     */
    @Value("${qiniu.ak}")
    private  String ak;

    /**
     * 账号SK
     */
    @Value("${qiniu.sk}")
    private String sk;

    /**
     * 要上传的空间名
     */
    @Value("${qiniu.bucketName}")
    private String bucketName;

    /**
     * 上传的域名
     */
    @Value("${qiniu.dominName}")
    private String dominName;

    /**
     * 根据File进行文件上传
     *
     * @param file 需要上传的文件
     * @param key  上传到文件空间里的key，即文件名称
     * @return string
     */
    public boolean upload(File file, String key) {
        Auth auth = Auth.create(ak, sk);
        com.qiniu.storage.Configuration configuration = new com.qiniu.storage.Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            String token = auth.uploadToken(bucketName);
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            Response res = uploadManager.put(file, key, token);
            return res.isOK();
        } catch (QiniuException e) {
            LOGGER.error(e.toString(), e);
        }
        return false;
    }

    /**
     * 根据byte[]进行上传
     *
     * @param fileBytes 需要上传的文件字节流
     * @param key  上传到文件空间里的key，即文件名称
     * @return string
     */
    public boolean upload(byte[] fileBytes, String key) {
        Auth auth = Auth.create(ak, sk);
        com.qiniu.storage.Configuration configuration = new com.qiniu.storage.Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            String token = auth.uploadToken(bucketName);
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            Response res = uploadManager.put(fileBytes, key, token);
            return res.isOK();
        } catch (QiniuException e) {
            LOGGER.error(e.toString(), e);
        }
        return false;
    }

    /**
     * 根据inputstream进行上传
     *
     * @param file 需要上传的字节输入流
     * @param key  上传到文件空间里的key，即文件名称
     * @return string
     */
    public boolean upload(InputStream file, String key) {
        Auth auth = Auth.create(ak, sk);
        com.qiniu.storage.Configuration configuration = new com.qiniu.storage.Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            String token = auth.uploadToken(bucketName);
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            Response res = uploadManager.put(file, key, token,null,null);
            return res.isOK();
        } catch (QiniuException e) {
            LOGGER.error(e.toString(), e);
        }
        return false;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getDominName() {
        return dominName;
    }

    public void setDominName(String dominName) {
        this.dominName = dominName;
    }

    @Override
    public String toString() {
        return "QiNiuUploadUntil{" +
                "ak='" + ak + '\'' +
                ", sk='" + sk + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", dominName='" + dominName + '\'' +
                '}';
    }
}
