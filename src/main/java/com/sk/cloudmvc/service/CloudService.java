package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.CloudMapper;
import com.sk.cloudmvc.model.Cloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiaochunxiang
 * @date 2020/4/8 14:42
 */
@Service
public class CloudService {

    @Autowired
    private CloudMapper cloudMapper;

    /**
    * 根据用户id获取所有的服务器
    *
    * @param userId 用户id
    * @return java.util.List<com.sk.cloudmvc.model.Cloud>
    * @author qiaochunxiang
    * @date 14:43 2020/4/8
    **/
    public List<Cloud> findAll(String userId){
        return cloudMapper.findAll(userId);
    }
}
