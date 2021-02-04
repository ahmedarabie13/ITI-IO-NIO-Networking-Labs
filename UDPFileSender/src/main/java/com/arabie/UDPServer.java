package com.arabie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPServer {

    public static void main(String args[]) throws InterruptedException {
        DatagramSocket dgSocket = null;


            int socketNumber = 5472;


        try {
            dgSocket = new DatagramSocket(socketNumber);
            while (true)
            {
                byte[] byteBuffer = new byte[1000];
                DatagramPacket dgRequest = new DatagramPacket(byteBuffer, byteBuffer.length);
                System.out.println("beforeReceive");
                dgSocket.receive(dgRequest);
                Runnable handler =new UDPServerHandler(dgRequest,dgSocket);
                Thread thread =new Thread(handler);
                thread.start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dgSocket != null)
                dgSocket.close();
        }




    }


}



