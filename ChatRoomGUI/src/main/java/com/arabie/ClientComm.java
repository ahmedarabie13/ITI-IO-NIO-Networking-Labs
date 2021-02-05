package com.arabie;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientComm {
    public Socket clientSocket;
//    Scanner in ;
    BufferedReader in;
    PrintWriter out;
    public ClientComm() throws IOException {
        clientSocket = new Socket("localhost", 8189);

        try  {
            InputStream inStream = clientSocket.getInputStream();
            OutputStream outStream = clientSocket.getOutputStream();
//            in = new Scanner(inStream, "UTF-8");
            in = new BufferedReader(new InputStreamReader(inStream));
            out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);

//            Thread sendingThread = new Thread(() -> {
//                String str="";
//                while (true) {
////                    str = sender.nextLine();
//                    out.println(str);
//                }
//            });
//            sendingThread.start();
//
//            String line;
//            while (true) {
//                if (in.hasNextLine()) {
//                    line = in.nextLine();
//                    System.out.println(line);
//                }
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void sendMessage(String msg){
        if( !clientSocket.isClosed() && clientSocket.isConnected())
            out.println(msg);
    }
    public String readMessage(){
        String message="";
        try {
//            while(!in.ready());
//            if(in.ready()){
                message= in.readLine();
                return message;
//            }
        } catch (IOException e) {

            e.printStackTrace();
        }
//
//        while (!in.hasNextLine());
////        {
//            System.out.println("in sending");
//            message = in.nextLine();
////        }
        return null;
    }
    public static void main(String[] args) throws IOException {


    }
}
