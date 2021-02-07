package com.arabie;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            HelloImpl obj = new HelloImpl();
            Registry reg = LocateRegistry.createRegistry(8158);
            reg.rebind("HelloService", obj);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
