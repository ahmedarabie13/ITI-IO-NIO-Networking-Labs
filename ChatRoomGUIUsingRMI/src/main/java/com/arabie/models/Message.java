package com.arabie.models;

import org.kordamp.ikonli.javafx.FontIcon;

import java.io.Serializable;

public class Message implements Serializable {
    private String msgBody;
    private String timeStamp;
    private FontIcon statusIcon;
    private User sender;

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Message(User sender, String msgBody, String timeStamp, FontIcon statusIcon) {
        this.sender = sender;
        this.msgBody = msgBody;
        this.timeStamp = timeStamp;
        this.statusIcon = statusIcon;
    }

    public FontIcon getStatusIcon() {
        return statusIcon;
    }

    public void setStatusIcon(FontIcon statusIcon) {
        this.statusIcon = statusIcon;
    }
}
