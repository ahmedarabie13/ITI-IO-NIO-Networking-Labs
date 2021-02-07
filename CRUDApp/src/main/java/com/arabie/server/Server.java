package com.arabie.server;

import com.arabie.server.dao.daoImpl.EmployeeDaoImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            EmployeeDaoImpl obj = new EmployeeDaoImpl();
            Registry reg = LocateRegistry.createRegistry(1427);

            reg.rebind("DBService", obj);

        } catch (RemoteException | SQLException ex) {
            ex.printStackTrace();
        }


    }
}