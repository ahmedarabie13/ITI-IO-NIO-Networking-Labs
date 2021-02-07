package com.arabie.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.arabie.App;
import com.arabie.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class PrimaryController {
    @FXML
    private ImageView imageId;
    @FXML
    private TextField userName;
    @FXML
    public void onClickYalla() throws IOException {

        App.switchToSecondary(new User(imageId.getImage(),userName.getText()));


//        App.switchToSecondary(new User(imageId.getImage(),userName.getText()));
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
//        fxmlLoader.setResources(new ResourceBundle() {
//            @Override
//            protected Object handleGetObject(String key) {
//                return new User(imageId.getImage(),userName.getText());
//            }
//
//            @Override
//            public Enumeration<String> getKeys() {
//                return null;
//            }
//        });
//        if(userName.getText().equals("")||userName.getText().length()>15){
//            Alert alert =new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Enter Valid Name");
//            alert.setHeaderText("Invalid Username");
//            alert.show();
//        }
//        else {
////            ((Stage)userName.getScene().getWindow()).setMinHeight(400);
////            ((Stage)userName.getScene().getWindow()).setMinWidth(300);
//            App.setRoot((Parent) fxmlLoader.load());
//        }

    }
    @FXML
    public void onEnterTyped() throws IOException {
        App.switchToSecondary(new User(imageId.getImage(),userName.getText()));
    }
    @FXML
    public void choosePicBtnHandler(){

        FileChooser fileChooser= new FileChooser();
        fileChooser.setTitle("Choose Your Pic");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile =fileChooser.showOpenDialog(App.getStage());
        if (selectedFile != null) {
            try (FileInputStream fileInputStream = new FileInputStream(selectedFile.getAbsolutePath())) {
//                imageId.setImage(new Image(fileInputStream,20,20,true,true));
                imageId.setImage(new Image(fileInputStream));
            }catch (IOException e){

            }

        }
    }
}
