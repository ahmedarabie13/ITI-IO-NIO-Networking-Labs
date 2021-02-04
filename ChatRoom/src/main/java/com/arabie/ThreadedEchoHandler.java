package com.arabie;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ThreadedEchoHandler implements Runnable {
    private Socket incoming;
    private String line;
    private List<PrintWriter> clientsOut;
    private volatile boolean isNotSent;

    public ThreadedEchoHandler(Socket incomingSocket, List<PrintWriter> clients) {
        incoming = incomingSocket;
        clientsOut = clients;
    }

    public void run() {

        try (InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream()) {

            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);

            Thread receivingThread = new Thread(() -> {
                while (true) {
                    if (in.hasNextLine()) {
                        line = in.nextLine();
                        isNotSent = true;
                    }
                }
            });
            receivingThread.start();
            while (true) {
                if (isNotSent) {
                    for (PrintWriter clientOut : clientsOut) {
                        clientOut.println("Server: " + line);
                    }
                    isNotSent = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}