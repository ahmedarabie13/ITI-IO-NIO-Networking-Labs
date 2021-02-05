package com.arabie;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ServerInstance implements Runnable {
    private Socket incoming;
    private String message;
    private List<PrintWriter> clientsOut;
    private volatile boolean isNotSent;
    private PrintWriter thisWriter;
    public ServerInstance(Socket incomingSocket, List<PrintWriter> clients,PrintWriter thisWriter) {
        incoming = incomingSocket;
        clientsOut = clients;
        this.thisWriter=thisWriter;

    }

    public void run() {

        try (InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream()) {

            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);

            Thread receivingThread = new Thread(() -> {
                while (true) {
                    if (in.hasNextLine()) {
                        message = in.nextLine();
                        isNotSent = true;
                        System.out.println("data Received: "+message);
                    }
                }
            });
            receivingThread.start();
            while (true) {
                if (isNotSent) {
                    for (PrintWriter clientOut : clientsOut) {
                        if(clientOut!=thisWriter)
                            clientOut.println(message);

                    }
                    System.out.println("Data to be sent: "+message);
                    isNotSent = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}