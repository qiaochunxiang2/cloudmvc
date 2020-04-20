package com.sk.cloudmvc.dao;

import com.sk.cloudmvc.model.UserInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author qiaochunxiang
 * @date 2020/3/26 16:13
 */
@Repository
@SuppressWarnings("unused")
public interface UserInformationMapper {

    /**
     * 按照id查找用户资料
     *
     * @param id 用户id
     * @return com.sk.cloudmvc.model.UserInformation
     * @author qiaochunxiang
     * @date 16:15 2020/3/26
     **/
    UserInformation findById(String id);

    /**
     * 注册功能，添加用户信息
     *
     * @param userInformation 用户信息
     * @author qiaochunxiang
     * @date 16:40 2020/4/1
     **/
    void addInformation(UserInformation userInformation);

    /**
     * 修改用户信息
     *
     * @param userInformation 用户信息
     * @author qiaochunxiang
     * @date 21:52 2020/4/3
     **/
    void updateInformation(UserInformation userInformation);

    /**
     * 修改头像地址
     *
     * @param id       用户id
     * @param imageUrl 头像地址
     * @author qiaochunxiang
     * @date 15:31 2020/4/5
     **/
    void updateImageUrl(@Param("id") String id, @Param("imageUrl") String imageUrl);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return boolean
     * @author qiaochunxiang
     * @date 16:50 2020/4/20
     **/
    boolean deleteUserInformation(String id);
}
