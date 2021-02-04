package com.arabie;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class SimpleSocketClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 1286);

        System.out.println("client ran successfully");
        InputStream sIn = s.getInputStream();
        DataInputStream socketDIS = new DataInputStream(sIn);
        System.out.println("Reading data.....");
        String testString = socketDIS.readUTF();
        System.out.println("Data received successfully");
        System.out.println(testString);

        System.out.println("Terminate the connection");


        socketDIS.close();
        sIn.close();
        s.close();
    }
}
