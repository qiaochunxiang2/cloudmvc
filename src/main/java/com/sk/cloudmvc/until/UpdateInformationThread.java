package com.sk.cloudmvc.until;

import com.sk.cloudmvc.dao.UserInformationMapper;

/**
 * @author qiaochunxiang
 * @date 2020/4/5 15:28
 */
public class UpdateInformationThread extends Thread {

    /**
     * 用户id
     */
    private String id;

    /**
     * 新头像位置
     */
    private String newKey;

    /**
     * userInformationMapper
     */
    private UserInformationMapper mapper;

    public UpdateInformationThread(String id, String newKey, UserInformationMapper mapper) {
        this.id = id;
        this.newKey = newKey;
        this.mapper = mapper;
    }

    @Override
    public void run() {
        mapper.updateImageUrl(id, newKey);
    }
}
