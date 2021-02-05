package com.arabie;

import java.io.IOException;
import java.net.URL;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;


public class SecondaryController implements Initializable {
    private User user;
    private ClientComm client;
    @FXML
    private Label userName;
    @FXML
    private ImageView userImage;
    @FXML
    private VBox chatWindow;
    @FXML
    private TextField textFieldMsg;
    @FXML
    private ScrollPane scrlPane;


    @FXML
    private void enterClicked() throws IOException {
        String msgText =textFieldMsg.getText();
        textFieldMsg.clear();
//        FXMLLoader loader =new FXMLLoader(App.class.getResource("chatMsg.fxml"));
//        Parent msg = loader.load();
//        ChatMsg msgCtrl =  loader.getController();
//        msgCtrl.setMsgBody(msgText);
//        msgCtrl.setSenderName(user.getName());
//        msgCtrl.setSenderImg(user.getImage());
        if (!msgText.equals("")) {

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            Message message =new Message(user,msgText, formattedDate, null);
            client.sendMessage(App.serializeMessage(message));
            Parent msg = App.addMsgItem(message);
            scrlPane.vvalueProperty().bind(chatWindow.heightProperty());
            chatWindow.prefWidthProperty().bind(scrlPane.widthProperty());
            chatWindow.getChildren().add(msg);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user= (User) resourceBundle.getObject("");
        userName.setText(user.getName());
//        userName.textProperty().bind();
        userImage.setImage(user.getImage());

        try {
            client = new ClientComm();
            Thread receivingThread = new Thread(()->{
                while (true){
                    String messageSerialized = client.readMessage();
                    System.out.println("data received: "+messageSerialized);
                    if(messageSerialized!=null&&!messageSerialized.equals("")){
                        Platform.runLater(()->{
                            try {
                                Message message = App.deserializeMessage(messageSerialized);
                                System.out.println("message: "+message.getMsgBody());
                                Parent msgItem=App.addMsgItem(message);
                                chatWindow.getChildren().add(msgItem);

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }
            });
            receivingThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}