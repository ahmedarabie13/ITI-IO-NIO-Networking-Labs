package com.arabie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPServerHandler implements Runnable{
    private DatagramPacket dgRequest;
    private DatagramSocket dgSocket;
    public UDPServerHandler(DatagramPacket dgRequest,DatagramSocket dgSocket){
        this.dgRequest = dgRequest;
        this.dgSocket=dgSocket;
    }
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(UDPServer.class.getResource("/file.txt").getPath()))) {

            Scanner scanner = new Scanner(bufferedReader);
            System.out.println("AfterRCV");
            byte[] byteBuffer = new byte[1000];

            while (true) {
                if (scanner.hasNextLine()) {
                    byteBuffer = scanner.nextLine().getBytes(StandardCharsets.UTF_8);
                    System.out.println(new String(byteBuffer));
                    DatagramPacket dgResponse = new DatagramPacket(byteBuffer, byteBuffer.length,
                            dgRequest.getAddress(), dgRequest.getPort());
                    dgSocket.send(dgResponse);
                    Thread.sleep(200);
                }

            }
        } catch (
                IOException | InterruptedException e) {
        System.out.println("IO Exception : " + e.getMessage());
    }
    }
}
