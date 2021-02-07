package com.arabie.network;

import com.arabie.models.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RMIServerImpl extends UnicastRemoteObject implements ServerInt {
    //todo concurrent
    ConcurrentLinkedQueue<ClientInt> clientsRef = new ConcurrentLinkedQueue<>();

    protected RMIServerImpl() throws RemoteException {
    }

    @Override
    public void tellOthers(Message msg,ClientInt client) throws RemoteException {

        for(ClientInt clientInt: clientsRef){
            if(!client.equals(clientInt)) {
                clientInt.receive(msg);
            }
        }

    }

    @Override
    public void register(ClientInt clientRef) throws RemoteException {
        clientsRef.add(clientRef);
    }

    @Override
    public void unRegister(ClientInt clientRef) throws RemoteException {
        clientsRef.remove(clientRef);
    }
}
