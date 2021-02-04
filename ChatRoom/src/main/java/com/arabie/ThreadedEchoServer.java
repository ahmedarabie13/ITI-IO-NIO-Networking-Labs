package com.arabie;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadedEchoServer {

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(8189)) {
            int i = 1;
            List<PrintWriter> clientsOut = new ArrayList<>();
            while (true) {
                Socket incoming = s.accept();
                PrintWriter newOut = new PrintWriter(new OutputStreamWriter(incoming.getOutputStream()
                        , "UTF-8"), true);
                clientsOut.add(newOut);
                System.out.println("Spawning " + i);

                Runnable r = new ThreadedEchoHandler(incoming, clientsOut);

                Thread t = new Thread(r);
                t.start();

                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}