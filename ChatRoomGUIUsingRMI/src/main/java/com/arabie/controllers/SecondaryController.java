package com.arabie.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.arabie.App;
import com.arabie.models.Message;
import com.arabie.models.User;
import com.arabie.network.ClientInt;
import com.arabie.network.RMIClientImpl;
import com.arabie.network.ServerInt;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class SecondaryController implements Initializable {
    private User user;
//    private ClientComm client;
    private ClientInt client;
    private ServerInt server;
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
        String msgText = textFieldMsg.getText();
        textFieldMsg.clear();
        if (!msgText.equals("")) {

            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            Message message = new Message(user, msgText, formattedDate, null);
//            server = new RMIServerImpl();
//            server.register(client);
            new Thread(()->{
                try {
                    server.tellOthers(message,client);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();
//            client.sendMessage(App.serializeMessage(message));
            Parent msg = App.addMsgItem(message);
            scrlPane.vvalueProperty().bind(chatWindow.heightProperty());
            chatWindow.prefWidthProperty().bind(scrlPane.widthProperty());
            chatWindow.getChildren().add(msg);

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User) resourceBundle.getObject("");
        userName.setText(user.getName());
        userImage.setImage(user.getImage());

        try {

            client=new RMIClientImpl(chatWindow);
            try{
                Registry reg= LocateRegistry.getRegistry(2374);
                server=(ServerInt) reg.lookup("RMIChat");
            }catch(Exception ex)
            {
                ex.printStackTrace();
            }
            server.register(client);
//            client = new ClientComm();
//            ClientInt rmiClient =new RMIClientImpl();


//            Thread receivingThread = new Thread(() -> {
//                while (true) {
//                    String messageSerialized = client.readMessage();
//                    System.out.println("data received: " + messageSerialized);
//                    if (messageSerialized != null && !messageSerialized.equals("")) {
//                        Platform.runLater(() -> {
//                            try {
//                                Message message = App.deserializeMessage(messageSerialized);
//                                System.out.println("message: " + message.getMsgBody());
//                                Parent msgItem = App.addMsgItem(message);
//                                chatWindow.getChildren().add(msgItem);
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } catch (ClassNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                        });
//                    }
//                }
//            });
//            receivingThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}