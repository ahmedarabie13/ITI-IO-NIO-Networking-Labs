package com.arabie.network;

import com.arabie.App;
import com.arabie.models.Message;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientImpl extends UnicastRemoteObject implements ClientInt {
    private VBox chatWindow;
    public RMIClientImpl(VBox chatWindow) throws RemoteException {
        super();
        this.chatWindow=chatWindow;

    }
    @Override
    public void receive(Message msg) throws RemoteException {
        Platform.runLater(()->{
            Parent message = null;
            try {
                message = App.addMsgItem(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            chatWindow.getChildren().add(message);
        });

    }

}
