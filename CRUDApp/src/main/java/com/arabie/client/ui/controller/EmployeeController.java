package com.arabie.client.ui.controller;

import com.arabie.models.Employee;
import com.arabie.server.dao.EmployeeDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
//import org.apache.commons.validator.routines.EmailValidator;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField middleNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;

    public EmployeeDAO employeeDAO;


    public EmployeeController() throws RemoteException, SQLException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            Registry reg = LocateRegistry.getRegistry(1427);
            employeeDAO = (EmployeeDAO) reg.lookup("DBService");;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private Boolean isDataComplete() {
        return !(firstNameField.getText().equals("") || Integer.parseInt(idField.getText()) == 0
                || lastNameField.getText().equals("") || middleNameField.getText().equals("")
                || emailField.getText().equals("") || phoneField.getText().equals(""));
    }

    @FXML
    private void insertButton() throws SQLException, RemoteException {
        Alert alert = new Alert(Alert.AlertType.NONE);

        Employee emp = new Employee();
        if (isDataComplete()) {
            alert.setContentText("Missing Some Fields");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.show();
        } else {

            emp.setId(Integer.parseInt(idField.getText()));
            emp.setFirstName(firstNameField.getText());
            emp.setLastName(lastNameField.getText());
            emp.setMiddleName(middleNameField.getText());
            emp.setEmail(emailField.getText());
            emp.setPhone(phoneField.getText());
            employeeDAO.save(emp);
            alert.setContentText("Data Inserted Successfully");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.show();

        }

    }

    @FXML
    private void updateButton() throws SQLException, RemoteException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (!isDataComplete()) {
            alert.setContentText("Missing Some Fields");
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.show();
        } else {
            Employee emp = new Employee();
            emp.setId(Integer.parseInt(idField.getText()));
            emp.setFirstName(firstNameField.getText());
            emp.setLastName(lastNameField.getText());
            emp.setMiddleName(middleNameField.getText());
            emp.setEmail(emailField.getText());
            emp.setPhone(phoneField.getText());
            employeeDAO.update(emp);

            alert.setContentText("Data Updated Successfully");
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.show();
            clearView();
        }

    }

    @FXML
    private void deleteButton() throws SQLException, RemoteException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(idField.getText()));
        int id = employee.getId();

        employeeDAO.delete(employee);
        alert.setContentText("Data Deleted Successfully");
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.show();
        clearView();
    }


    public void firstButton(ActionEvent actionEvent) throws SQLException, RemoteException {
        Employee emp;
        emp = employeeDAO.getFirst();
        updateView(emp);
    }

    public void selectAllButton(ActionEvent actionEvent) throws SQLException, RemoteException {
        List<Employee> people;
        people = employeeDAO.selectAll();


        for (Employee p : people) {
            System.out.println(p);
        }
    }


    public void lastButton(ActionEvent actionEvent) throws SQLException, RemoteException {
        Employee emp ;
        emp = employeeDAO.getLast();
        updateView(emp);
    }

    private void updateView(Employee emp) {
        idField.setText(String.valueOf(emp.getId()));
        firstNameField.setText(emp.getFirstName());
        middleNameField.setText(emp.getMiddleName());
        lastNameField.setText(emp.getLastName());
        emailField.setText(emp.getEmail());
        phoneField.setText(emp.getPhone());
    }


    public void nextButton(ActionEvent actionEvent) throws SQLException, RemoteException {
        Employee emp ;
        emp = employeeDAO.getNext(Integer.valueOf(idField.getText()));
        updateView(emp);


    }


    public void previousButton(ActionEvent actionEvent) throws SQLException, RemoteException {
        Employee previousEmployee = employeeDAO.getPrevious(Integer.valueOf(idField.getText()));
        updateView(previousEmployee);
    }


    private void clearView() {
        idField.clear();
        firstNameField.clear();
        middleNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();

    }

}
