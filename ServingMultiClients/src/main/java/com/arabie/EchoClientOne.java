package com.arabie;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class EchoClientOne {


    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 8189);

        try (InputStream inStream = clientSocket.getInputStream();
             OutputStream outStream = clientSocket.getOutputStream()) {
//telnet
            
            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);

            Scanner sender = new Scanner(System.in, "UTF-8");
            String str = "";
            while (!str.equals("stop") && in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
//                System.out.print("client: ");
                str = sender.next();
                out.println(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
