package com.arabie.server.dao;

import com.arabie.models.Employee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends Remote {

    Boolean save(Employee employee) throws SQLException, RemoteException;
    Boolean update(Employee employee) throws SQLException, RemoteException;
    Boolean delete(Employee employee) throws SQLException, RemoteException;

    Employee getFirst() throws SQLException, RemoteException;
    Employee getLast() throws SQLException, RemoteException;
    Employee getPrevious(Integer employeeId) throws SQLException, RemoteException;
    Employee getNext(Integer employeeId) throws SQLException, RemoteException;

    List<Employee> selectAll() throws SQLException, RemoteException;

}
