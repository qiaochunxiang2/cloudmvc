package com.sk.cloudmvc.until;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiaochunxiang
 * @date 2020/4/27 14:46
 */
@Configuration
public class LinuxUntil {

    /**
     * 执行linux命令，成功返回true，失败返回false，其他情况返回异常
     *
     * @param host    地址
     * @param user    用户名
     * @param psw     密码
     * @param port    端口
     * @param command 命令
     * @author qiaochunxiang
     * @date 14:48 2020/4/27
     **/
    public State exec(String host, String user, String psw, int port, String command) throws JSchException {
        ChannelExec openChannel = null;
        Session session = null;
        try {
            session = connect(host, user, psw, port);
            if (session == null) {
                return State.PASSWORD_ERROR;
            } else {
                openChannel = (ChannelExec) session.openChannel("exec");
                openChannel.setCommand(command);
                openChannel.connect();
            }
        } finally {
            if (openChannel != null && !openChannel.isClosed()) {
                openChannel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return State.SUCCESS;
    }

    public Session connect(String host, String user, String psw, int port) {
        Session session;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.connect();
        } catch (JSchException e) {
            session = null;
        }
        return session;
    }
}
