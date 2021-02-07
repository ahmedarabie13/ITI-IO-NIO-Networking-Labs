package com.arabie.network;

import com.arabie.models.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInt extends Remote {

    void receive(Message msg) throws RemoteException;

}
