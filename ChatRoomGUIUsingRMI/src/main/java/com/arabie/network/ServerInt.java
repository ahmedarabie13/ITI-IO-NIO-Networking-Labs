package com.arabie.network;

import com.arabie.models.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInt extends Remote {

    void tellOthers(Message msg,ClientInt clientInt) throws RemoteException;
    void register(ClientInt clientRef) throws RemoteException;
    void unRegister(ClientInt clientRef) throws RemoteException;

}
