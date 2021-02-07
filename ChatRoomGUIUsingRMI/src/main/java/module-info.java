module com.arabie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
    requires javafx.swing;
    requires java.rmi;

    opens com.arabie to javafx.fxml;
    opens com.arabie.network;
    opens com.arabie.controllers;
    opens com.arabie.models;


    exports com.arabie;
}