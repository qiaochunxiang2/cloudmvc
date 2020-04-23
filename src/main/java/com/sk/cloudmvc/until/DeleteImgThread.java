package com.sk.cloudmvc.until;

/**
 * @author qiaochunxiang
 * @date 2020/4/5 15:20
 */
public class DeleteImgThread extends Thread {

    /**
     * 旧文件
     */
    private String oldKey;

    /**
     * 七牛云工具类
     */
    private QiNiuUploadUntil qiNiuUploadUntil;

    public DeleteImgThread(String oldKey, QiNiuUploadUntil qiNiuUploadUntil) {
        this.oldKey = oldKey;
        this.qiNiuUploadUntil = qiNiuUploadUntil;
    }

    @Override
    public void run() {
        // 删除旧文件并进行刷新
        boolean deleteResult = qiNiuUploadUntil.delete(oldKey);
        if (deleteResult) {
            String deleteUrl = qiNiuUploadUntil.getDominName() + oldKey;
            qiNiuUploadUntil.refreshUrls(deleteUrl);
        }
    }
}
