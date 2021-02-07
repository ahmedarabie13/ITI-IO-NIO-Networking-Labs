package com.arabie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * JavaFX App
 */


//auto closa
public class App extends Application {

    @Override
    public void start(Stage stage) {
        WebView webView =new WebView();
        TextField txtField = new TextField();
        try
        {
            String urlName;
            urlName= "https://www.google.com";
            URL url= new URL(urlName);
            URLConnection connection= url.openConnection();
            connection.connect();

            Map<String, List<String>> headers= connection.getHeaderFields();
            for(Map.Entry<String, List<String>> entry: headers.entrySet())
            {
                String key= entry.getKey();
                for(String value: entry.getValue())
                    System.out.println(key+ ": "+ value);
            }
            String encoding= connection.getContentEncoding();

            if(encoding== null)
                encoding= "UTF-8";

            try(Scanner in= new Scanner(connection.getInputStream(), encoding))
            {
                StringBuilder stringBuilder =new StringBuilder("");
                while (in.hasNextLine()){
                    stringBuilder.append(in.nextLine());
                }
//                webView.getEngine().load(urlName);
                webView.getEngine().loadContent(stringBuilder.toString(),"text/html");

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();

        }
        VBox vBox =new VBox();
        vBox.getChildren().addAll(txtField,webView);
        var scene = new Scene(vBox, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}