package com.arabie.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        new RMIServer();
    }

    public RMIServer() {
        try {
           RMIServerImpl obj =new RMIServerImpl();
            Registry reg = LocateRegistry.createRegistry(2374);
            reg.rebind("RMIChat", obj);
        } catch (RemoteException ex) {

        }
    }
}
