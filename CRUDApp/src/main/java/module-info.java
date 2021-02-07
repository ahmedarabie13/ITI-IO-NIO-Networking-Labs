module EmployeeCRUD {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.rmi;
    requires java.sql;
    requires mysql.connector.java;
   // requires commons.beanutils;
   // requires commons.validator;
    requires java.naming;


    opens com.arabie.client.ui.controller;
    exports com.arabie.client.ui.controller;
   // exports eg.gov.iti.jets.employeeCRUD.server.dto.dtoImpl;
    exports com.arabie.client;
    exports com.arabie.server.dao;
    exports com.arabie.models;
}