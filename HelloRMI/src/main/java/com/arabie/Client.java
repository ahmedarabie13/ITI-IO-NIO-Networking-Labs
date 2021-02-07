package com.arabie;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        new Client();
    }

    public Client() {

        try {
            Registry reg =
                    LocateRegistry.getRegistry(8158);
            HelloInt helloRef = (HelloInt)
                    reg.lookup("HelloService");
            System.out.println("All Connected Services: "+
                    Arrays.toString(reg.list()));
            String str = helloRef.sayHello("JETS");
            System.out.println(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}