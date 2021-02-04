package com.arabie;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UDPClientOne {
    public static void main(String args[]) {
//pass the message, server host and port number as arguments
        DatagramSocket dgSocket = null;

        try {
            dgSocket = new DatagramSocket();
            byte[] bytes = "ack".getBytes(StandardCharsets.UTF_8);

            InetAddress serverHost = InetAddress.getLocalHost();
            int serverPortNumber = 5472;

            DatagramPacket dgRequest = new DatagramPacket(bytes, bytes.length, serverHost, serverPortNumber);
            dgSocket.send(dgRequest);

            byte[] byteBuffer = new byte[1000];

            DatagramPacket dgResponse = new DatagramPacket(byteBuffer, byteBuffer.length);
            FileWriter fileWriter =new FileWriter("output1.txt");
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            PrintWriter printWriter =new PrintWriter(bufferedWriter);
            System.out.println("beforeLoop");
            String str;
            while (true){


                dgSocket.receive(dgResponse);
                str = new String(dgResponse.getData(),StandardCharsets.UTF_8);
                str=str.trim()+"\n";
                System.out.println(str+"\t "+str.length());


                bufferedWriter.write(str);
                bufferedWriter.flush();

                dgResponse.setData(new byte[1000]);
//                fileWriter.append(str);
//                printWriter.println(str);

            }
//            System.out.println("Datagram Response: " + new String(dgResponse.getData()));

        } catch (SocketException e) {
            System.out.println("Socket Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception : " + e.getMessage());
        } finally {
            if (dgSocket != null)
                dgSocket.close();
        }
    }
}

