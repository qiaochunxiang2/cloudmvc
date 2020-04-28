package com.sk.cloudmvc.dao;

import com.sk.cloudmvc.model.Cloud;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qiaochunxiang
 * @date 2020/4/8 14:39
 */
@Repository
public interface CloudMapper {

    /**
     * 查询用户所有的服务器
     *
     * @param userId 用户id
     * @return java.util.List<com.sk.cloudmvc.model.Cloud>
     * @author qiaochunxiang
     * @date 14:42 2020/4/8
     **/
    List<Cloud> findAll(String userId);

    /**
     * 添加服务器
     *
     * @param cloud 服务器信息
     * @return boolean
     * @author qiaochunxiang
     * @date 14:21 2020/4/27
     **/
    boolean addCloud(Cloud cloud);

    /**
     * 关机
     *
     * @param cloud 服务器信息
     * @return boolean
     * @author qiaochunxiang
     * @date 15:30 2020/4/28
     **/
    boolean shutdown(Cloud cloud);
}
