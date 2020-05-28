package com.sk.cloudmvc.until;

import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.storage.BucketManager;
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
    private String ak;

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
     * 机房地址
     */
    @Value("${qiniu.address}")
    private Address address;

    public enum Address {
        // 华北服务器
        HUABEI,
        // 华南服务器
        HUANAN,
        // 华东服务器
        HUADONG,
        // 北美服务器
        BEIMEI,
        // 东南亚服务器
        DONGNANYA
    }

    private Auth getAuth() {
        return Auth.create(ak, sk);
    }

    public com.qiniu.storage.Configuration getConfiguration() {
        com.qiniu.storage.Configuration configuration;
        switch (address) {
            case HUADONG:
                configuration = new com.qiniu.storage.Configuration(Region.huadong());
                break;
            case HUABEI:
                configuration = new com.qiniu.storage.Configuration(Region.huabei());
                break;
            case HUANAN:
                configuration = new com.qiniu.storage.Configuration(Region.huanan());
                break;
            case BEIMEI:
                configuration = new com.qiniu.storage.Configuration(Region.beimei());
                break;
            case DONGNANYA:
                configuration = new com.qiniu.storage.Configuration(Region.xinjiapo());
                break;
            default:
                configuration = new com.qiniu.storage.Configuration(Region.autoRegion());
        }
        return configuration;
    }

    /**
     * 文件上传
     *
     * @param file     需要上传的文件
     * @param fileName 上传到文件空间里的key，即文件名称
     * @return string
     */
    public boolean upload(Object file, String fileName) {
        Auth auth = getAuth();
        com.qiniu.storage.Configuration configuration = getConfiguration();
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            // 覆盖上传
            String token = auth.uploadToken(bucketName, fileName);
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            Response res;
            if (file instanceof File) {
                res = uploadManager.put((File) file, fileName, token);
            } else if (file instanceof InputStream) {
                res = uploadManager.put((InputStream) file, fileName, token, null, null);
            } else {
                res = uploadManager.put((byte[]) file, fileName, token);
            }
            return res.isOK();
        } catch (QiniuException e) {
            LOGGER.error(e.toString(), e);
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @return boolean
     * @author qiaochunxiang
     * @date 10:06 2020/4/4
     **/
    public boolean delete(String fileName) {
        Auth auth = getAuth();
        com.qiniu.storage.Configuration configuration = getConfiguration();
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            if (StringUtils.isEmpty(fileName)) {
                return false;
            }
            Response res = bucketManager.delete(bucketName, fileName);
            return res.isOK();
        } catch (QiniuException e) {
            LOGGER.error(e.toString(), e);
        }
        return false;
    }

    /**
     * 刷新文件名
     *
     * @param urls 刷新的文件url
     * @return boolean
     * @author qiaochunxiang
     * @date 10:07 2020/4/4
     **/
    public boolean refreshUrls(String... urls) {
        Auth auth = Auth.create(ak, sk);
        CdnManager cdnManager = new CdnManager(auth);
        try {
            CdnResult.RefreshResult result = cdnManager.refreshUrls(urls);
            return result.code == 200;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "QiNiuUploadUntil{" +
                "ak='" + ak + '\'' +
                ", sk='" + sk + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", dominName='" + dominName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
