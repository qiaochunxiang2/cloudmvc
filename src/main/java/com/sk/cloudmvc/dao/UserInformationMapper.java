package com.sk.cloudmvc.dao;

import com.sk.cloudmvc.model.UserInformation;
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
}
