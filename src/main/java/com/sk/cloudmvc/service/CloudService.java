package com.sk.cloudmvc.service;

import com.sk.cloudmvc.dao.CloudMapper;
import com.sk.cloudmvc.model.Cloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public List<Cloud> findAll(String userId) {
        return cloudMapper.findAll(userId);
    }

    /**
    * 申请服务器
    *
    * @param cloud 服务器信息
    * @return boolean
    * @author qiaochunxiang
    * @date 14:39 2020/4/27
    **/
    public boolean addCloud(Cloud cloud) {
        String id = UUID.randomUUID().toString().replace("-", "");
        cloud.setId(id);
        cloud.setIp("192.168.239.129");
        Date startDate = new Date();
        cloud.setStartDate(startDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.YEAR, 1);
        Date endTime = cal.getTime();
        cloud.setEndDate(endTime);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cloudMapper.addCloud(cloud);
    }
}
