package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.UserInformationMapper;
import com.sk.cloudmvc.dao.UserMapper;
import com.sk.cloudmvc.model.User;
import com.sk.cloudmvc.model.UserInformation;
import com.sk.cloudmvc.until.MD5;
import com.sk.cloudmvc.until.QiNiuUploadUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

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

    public User login(Map<String, Object> jsonData) {
        String username = jsonData.get("username").toString();
        String pd = jsonData.get("password").toString();
        String password = MD5.encoderByMd5(pd);
        return userMapper.login(username, password);
    }

    public long changePassword(Map<String, Object> jsonData) {
        String oldPassword = (String) jsonData.get("oldPassword");
        String newPassword = (String) jsonData.get("newPassword");
        jsonData.put("oldPassword", MD5.encoderByMd5(oldPassword));
        jsonData.put("newPassword", MD5.encoderByMd5(newPassword));
        return userMapper.changePassword(jsonData);
    }

    @Transactional(rollbackFor = Exception.class)
    public void register(Map<String, String> jsonData){
        String username = jsonData.get("username");
        String pd = jsonData.get("password");
        String password = MD5.encoderByMd5(pd);
        String id = UUID.randomUUID().toString().replace("-","");
        User user = new User(id, username, password);
        UserInformation information = new UserInformation();
        information.setId(id);
        String imageUrl = qiNiuUploadUntil.getDominName() + id;
        information.setImageUrl(imageUrl);
        userMapper.addUser(user);
        userInformationMapper.addInformation(information);
    }

    public boolean updatePhoto(MultipartFile file, String id) {
        try (InputStream inputStream = file.getInputStream()){
           return qiNiuUploadUntil.upload(inputStream, id);
        } catch (Exception e){
            throw new RuntimeException(e.toString(), e);
        }
    }
}
