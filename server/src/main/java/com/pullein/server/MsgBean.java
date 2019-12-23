package com.pullein.server;

/**
 * 聊天消息实体
 */
public class MsgBean {
    //客户端发送时间
    private long clientSendTime;
    //会话ID
    private String sessionId;
    //昵称
    private String nikeName;
    //消息内容
    private String msgContent;

    public long getClientSendTime() {
        return clientSendTime;
    }

    public void setClientSendTime(long clientSendTime) {
        this.clientSendTime = clientSendTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MsgBean) {
            //这里粗略的认为客户端发送时间戳一致且sessionId一致则为同一条消息
            return clientSendTime == ((MsgBean) o).clientSendTime && StringUtil.equals(sessionId, ((MsgBean) o).sessionId);
        }
        return false;
    }

    @Override
    public String toString() {
        return "MsgBean{" +
                "clientSendTime=" + clientSendTime +
                ", sessionId='" + sessionId + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", msgContent='" + msgContent + '\'' +
                '}';
    }
}
