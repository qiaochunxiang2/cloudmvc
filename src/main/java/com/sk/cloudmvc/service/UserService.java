package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.UserInformationMapper;
import com.sk.cloudmvc.dao.UserMapper;
import com.sk.cloudmvc.model.User;
import com.sk.cloudmvc.model.UserInformation;
import com.sk.cloudmvc.until.DeleteImgThread;
import com.sk.cloudmvc.until.MD5Until;
import com.sk.cloudmvc.until.QiNiuUploadUntil;
import com.sk.cloudmvc.until.UpdateInformationThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author qiaochunxiang
 * @date 2020/3/24 15:19
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInformationMapper userInformationMapper;

    @Autowired
    private QiNiuUploadUntil qiNiuUploadUntil;

    private static ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(5);

    /**
     * 登录
     *
     * @param jsonData 前端数据
     * @return com.sk.cloudmvc.model.User
     * @author qiaochunxiang
     * @date 15:17 2020/3/24
     **/
    public User login(Map<String, Object> jsonData) {
        String username = jsonData.get("username").toString();
        String pd = jsonData.get("password").toString();
        String password = MD5Until.encoderByMd5(pd);
        return userMapper.login(username, password);
    }

    /**
     * 修改密码
     *
     * @param jsonData 前端数据
     * @return long
     * @author qiaochunxiang
     * @date 14:48 2020/4/1
     **/
    public long changePassword(Map<String, Object> jsonData) {
        String oldPassword = (String) jsonData.get("oldPassword");
        String newPassword = (String) jsonData.get("newPassword");
        jsonData.put("oldPassword", MD5Until.encoderByMd5(oldPassword));
        jsonData.put("newPassword", MD5Until.encoderByMd5(newPassword));
        return userMapper.changePassword(jsonData);
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @author qiaochunxiang
     * @date 16:41 2020/4/1
     **/
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        String pd = user.getPassword();
        String password = MD5Until.encoderByMd5(pd);
        String id = UUID.randomUUID().toString().replace("-", "");
        user.setId(id);
        user.setPassword(password);
        UserInformation information = user.getInformation();
        information.setId(id);
        userMapper.addUser(user);
        userInformationMapper.addInformation(information);
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

    /**
     * 修改用户资料
     *
     * @param information 用户信息
     * @author qiaochunxiang
     * @date 21:58 2020/4/3
     **/
    public void updateInformation(UserInformation information) {
        userInformationMapper.updateInformation(information);
    }


    /**
     * @return java.util.List<com.sk.cloudmvc.model.User>
     * @author qiaochunxiang
     * @date 15:46 2020/4/20
     **/
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return boolean
     * @author qiaochunxiang
     * @date 16:52 2020/4/20
     **/
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUser(String id) {
        boolean result1 = userMapper.deleteUser(id);
        boolean result2 = userInformationMapper.deleteUserInformation(id);
        return result1 && result2;
    }
}
