package com.arabie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class App {
    public static void main(String[] args) throws IOException {
        String strArr[] = new String[100];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Text, 'stop' to quit.");

        for (int i = 0; i < 100; i++) {
            strArr[i] = br.readLine();

            if (strArr[i].toLowerCase().equals("stop"))
                break;

        }

        for (String str : strArr) {
            if (str != null)
                System.out.println(str);
        }

    }
}