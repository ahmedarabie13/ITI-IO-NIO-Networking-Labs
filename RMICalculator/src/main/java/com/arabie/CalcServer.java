package com.arabie;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalcServer {
    public static void main(String[] args) {

        new CalcServer();

    }

    public CalcServer() {
        try {
            CalcImpl obj = new CalcImpl();
            Registry reg = LocateRegistry.createRegistry(1875);
            reg.rebind("CalculatorRMI", obj);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
