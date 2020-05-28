package com.sk.cloudmvc.service;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.sk.cloudmvc.dao.CloudMapper;
import com.sk.cloudmvc.model.Cloud;
import com.sk.cloudmvc.until.CloudIPUntil;
import com.sk.cloudmvc.until.LinuxUntil;
import com.sk.cloudmvc.until.State;
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

    @Autowired
    private LinuxUntil linuxUntil;

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
        if (CloudIPUntil.ips.size() == 0) {
            return false;
        }
        String ip = CloudIPUntil.ips.get(0);
        cloud.setIp(ip);
        CloudIPUntil.ips.remove(0);
        Date startDate = new Date();
        cloud.setStartDate(startDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.YEAR, 1);
        Date endTime = cal.getTime();
        cloud.setEndDate(endTime);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.toString(), e);
        }
        return cloudMapper.addCloud(cloud);
    }

    /**
     * 关机
     *
     * @param cloud 服务器信息
     * @return boolean
     * @author qiaochunxiang
     * @date 15:28 2020/4/28
     **/
    public State shutdown(Cloud cloud) {
        String ip = cloud.getIp();
        String password = cloud.getPassword();
        String command = "shutdown -h now";
        State result;
        try {
            result = linuxUntil.exec(ip, "root", password, 22, command);
            if (result == State.SUCCESS) {
                cloudMapper.shutdown(cloud);
            }
        } catch (Exception e) {
            result = State.CLOUD_ERROR;
        }
        return result;
    }

    /**
     * 服务器重启
     *
     * @param cloud 服务器信息
     * @return boolean
     * @author qiaochunxiang
     * @date 16:12 2020/4/28
     **/
    public boolean restart(Cloud cloud) {
        String ip = cloud.getIp();
        String password = cloud.getPassword();
        String command = "shutdown -r now";
        State result;
        boolean restart = false;
        try {
            result = linuxUntil.exec(ip, "root", password, 22, command);
            if (result == State.SUCCESS) {
                Thread.sleep(1000 * 20);
                int forLength = 60;
                for (int i = 1; i < forLength; i++) {
                    Session connect = linuxUntil.connect(ip, "root", password, 22);
                    if (connect != null) {
                        restart = true;
                        break;
                    }
                    Thread.sleep(1000 * 3);
                }
            }
        } catch (JSchException | InterruptedException e) {
            throw new RuntimeException(e.toString(), e);
        }
        return restart;
    }

    /**
     * 服务器卸载
     *
     * @param id 服务器id
     * @return boolean
     * @author qiaochunxiang
     * @date 16:11 2020/5/28
     **/
    public boolean delete(String id) throws InterruptedException {
        Thread.sleep(5000);
        return cloudMapper.deleteCloud(id);
    }
}
