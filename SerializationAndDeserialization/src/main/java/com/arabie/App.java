package com.arabie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;


/**
 * JavaFX App
 */
public class App {

    public static void serialize() {
        try(ObjectOutputStream objOStrm= new ObjectOutputStream(new FileOutputStream("serial"))) {
            MyClass myObject= new MyClass("Hello", -7, 2.7e10);
            System.out.println("Doing serialization");
            System.out.println("myObject: "+ myObject);
            objOStrm.writeObject(myObject);
        } catch(IOException e) {
            System.out.println("Exception during serialization: "+ e);
        }
    }

    public static void deserialize() {
        try(ObjectInputStream objIStrm= new ObjectInputStream(new FileInputStream("serial"))) {
            MyClass myObject= (MyClass) objIStrm.readObject();
            System.out.println("Doing deserialization");
            System.out.println("myObject: "+ myObject);
        } catch(Exception e) {
            System.out.println("Exception during deserialization: "+ e);
        }
    }

    public static void main(String args[]) {

       serialize();
       deserialize();
    }
}


