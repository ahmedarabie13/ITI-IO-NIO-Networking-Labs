package com.arabie;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer {
    public static void main(String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1286);
        System.out.println("Server ran successfully");
        Socket socket = serverSocket.accept();
        System.out.println("connection established");
        OutputStream socketOutStream = socket.getOutputStream();
        DataOutputStream socketDOS = new DataOutputStream(socketOutStream);
        System.out.println("Sending the data....");
        socketDOS.writeUTF("Hello world!");

        System.out.println("Data sent successfully");
        System.out.println("Terminate the connection");
        socketDOS.close();
        socketOutStream.close();
        socket.close();
        serverSocket.close();
    }
}