package com.arabie;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientTwo {


    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 8189);

        try (InputStream inStream = clientSocket.getInputStream();
             OutputStream outStream = clientSocket.getOutputStream()) {

            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);

            Scanner sender = new Scanner(System.in, "UTF-8");

            Thread sendingThread = new Thread(() -> {
                String str;
                while (true) {
                    str = sender.nextLine();
                    out.println(str);
                }
            });
            sendingThread.start();
            String line;
            while (true) {
                if (in.hasNextLine()) {
                    line = in.nextLine();
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
