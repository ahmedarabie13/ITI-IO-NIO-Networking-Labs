package com.arabie;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Base64;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class App extends Application {

    private static Scene scene;
    private static Stage window;
    private static User currentUser;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene = new Scene(loadFXML("primary"), 563, 289);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(Parent parent) throws IOException {
//        scene.setRoot(parent);
//        Scene secondaryScene =new Scene(parent);
//        secondaryScene.setFill();
//        scene=secondaryScene;
        window.setScene(scene = new Scene(parent));
        window.setResizable(true);
        window.setMinHeight(400);
        window.setMinWidth(300);
    }

    static Parent addMsgItem(Message message) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("chatMsg.fxml"));
        Parent msg = loader.load();
        ChatMsg msgCtrl = loader.getController();
        msgCtrl.setMsgBody(message.getMsgBody());
        System.out.println(message.getSender().getName());
        msgCtrl.setSenderName(message.getSender().getName());
        msgCtrl.setSenderImg(message.getSender().getImage());
        msgCtrl.setTimeStamp(message.getTimeStamp());

        return msg;
    }

    static void switchToSecondary(User user) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
        fxmlLoader.setResources(new ResourceBundle() {
            @Override
            protected Object handleGetObject(String key) {
                return user;
            }

            @Override
            public Enumeration<String> getKeys() {
                return null;
            }
        });
        if (user.getName().equals("") || user.getName().length() > 15) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Valid Name");
            alert.setHeaderText("Invalid Username");
            alert.show();
        } else {
            currentUser = user;
            setRoot((Parent) fxmlLoader.load());
        }
    }

    public static Message deserializeMessage(String encodedMsgStr) throws IOException,ClassNotFoundException {

        byte[] decodedMsgBytes = Base64.getDecoder().decode(encodedMsgStr);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(decodedMsgBytes));
        Object message = objectInputStream.readObject();
        objectInputStream.close();
        return (Message) message;

    }

    public static String serializeMessage(Message message) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(message);
        objectOutputStream.close();
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

    }

    static Stage getStage() {
        return (Stage) scene.getWindow();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}