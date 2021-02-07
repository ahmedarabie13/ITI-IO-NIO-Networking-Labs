package com.arabie.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class Client extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/PrimaryView.fxml"));
        Parent item = fxmlLoader.load();
        Scene scene = new Scene(item);

        stage.setScene(scene);
        stage.setTitle("Employee Data");
        stage.show();
        stage.setResizable(false);

    }

    public static void main(String []args) throws RemoteException, SQLException {

            launch(args);

        }

}
