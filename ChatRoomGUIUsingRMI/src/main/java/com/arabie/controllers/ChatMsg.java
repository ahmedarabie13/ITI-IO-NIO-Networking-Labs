package com.arabie.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.kordamp.ikonli.javafx.FontIcon;

public class ChatMsg  {

    @FXML
    private Label msgBody;
    @FXML
    private Label senderName;
    @FXML
    private ImageView senderImg;
    @FXML
    private FontIcon statusIcon;
    @FXML
    private Label timeStamp;

    public Label getMsgBody() {
        return msgBody;
    }

    public Label getSenderName() {
        return senderName;
    }

    public ImageView getSenderImg() {
        return senderImg;
    }

    public void setSenderName(String senderName) {

        this.senderName.setText(senderName);
    }

    public void setMsgBody(String metaBody) {

        this.msgBody.setText(metaBody);
    }

    public void setSenderImg(Image img) {

        this.senderImg.setImage(img);
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp.setText(timeStamp);
    }
}
