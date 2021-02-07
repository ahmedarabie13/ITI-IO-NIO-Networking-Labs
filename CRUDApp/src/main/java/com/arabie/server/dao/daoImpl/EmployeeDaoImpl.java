package com.arabie.server.dao.daoImpl;


import com.arabie.models.Employee;
import com.arabie.server.db.MyDataSourceFactory;
import com.arabie.server.dao.EmployeeDAO;

import javax.sql.DataSource;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDaoImpl extends UnicastRemoteObject implements EmployeeDAO {
    DataSource dataSource = MyDataSourceFactory.getMySQLDataSource();
    Connection connection;

    public EmployeeDaoImpl() throws RemoteException, SQLException {
        connection = dataSource.getConnection();
    }

    @Override
    public Boolean save(Employee employee) throws SQLException, RemoteException {


        final String SQL_INSERT = "INSERT INTO employee (`id`, `firstName`, `middleName`, `lastName`, `email`, `phone`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_INSERT);

            pStmt.setInt(1, employee.getId());
            pStmt.setString(2, employee.getFirstName());
            pStmt.setString(3, employee.getMiddleName());
            pStmt.setString(4, employee.getLastName());
            pStmt.setString(5, employee.getEmail());
            pStmt.setString(6, employee.getPhone());
            int row = pStmt.executeUpdate();
            if (row != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean update(Employee employee) throws SQLException, RemoteException {

        String SQL_UPDATE = "UPDATE employee SET firstName =? , middleName = ?, lastName = ?, email = ?, phone = ? WHERE (id = ?);";
        try {
            PreparedStatement pStmt = connection.prepareStatement(SQL_UPDATE);

            pStmt.setString(1, employee.getFirstName());
            pStmt.setString(2, employee.getMiddleName());
            pStmt.setString(3, employee.getLastName());
            pStmt.setString(4, employee.getEmail());
            pStmt.setString(5, employee.getPhone());
            pStmt.setInt(6, employee.getId());
            int row = pStmt.executeUpdate();
            if (row != 0)
                return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean delete(Employee employee) throws SQLException, RemoteException {
        String SQL_UPDATE = "DELETE FROM employee WHERE (id = ?);";
        PreparedStatement pStmt = connection.prepareStatement(SQL_UPDATE);

        pStmt.setInt(1, employee.getId());
        int row = pStmt.executeUpdate();
        if (row != 0)
            return true;
        else
            return null;
    }

    public Employee getFirst() throws SQLException, RemoteException {

        Employee emp = new Employee();

        String sql = "SELECT * FROM employee";
        PreparedStatement pStmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pStmt.executeQuery();
        rs.beforeFirst();
        if (rs.next()) {
            emp.setId(rs.getInt(1));
            emp.setFirstName(rs.getString(2));
            emp.setMiddleName(rs.getString(3));
            emp.setLastName(rs.getString(4));
            emp.setEmail(rs.getString(5));
            emp.setPhone(rs.getString(6));

        }

        return emp;
    }

    @Override
    public Employee getLast() throws SQLException, RemoteException {

        Connection connection = dataSource.getConnection();


        Employee emp = new Employee();

        String sql = "SELECT * FROM employee ORDER BY ID DESC LIMIT 1";

        PreparedStatement pStmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pStmt.executeQuery();
        rs.afterLast();
        if (rs.previous()) {
            emp.setId(rs.getInt(1));
            emp.setFirstName(rs.getString(2));
            emp.setMiddleName(rs.getString(3));
            emp.setLastName(rs.getString(4));
            emp.setEmail(rs.getString(5));
            emp.setPhone(rs.getString(6));
        }

        return emp;
    }

    @Override
    public Employee getPrevious(Integer employeeId) throws SQLException, RemoteException {

        Employee emp = new Employee();
        String sql = "SELECT id, firstname, middlename, lastname, email, phone FROM employee;";


        PreparedStatement pStmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pStmt.executeQuery();
        while (rs.next()) {
            if (employeeId == rs.getInt(1)) {
                rs.previous();
                emp.setId(rs.getInt(1));
                emp.setFirstName(rs.getString(2));
                emp.setMiddleName(rs.getString(3));
                emp.setLastName(rs.getString(4));
                emp.setEmail(rs.getString(5));
                emp.setPhone(rs.getString(6));
                break;
            }
        }
        return emp;
    }


    @Override
    public Employee getNext(Integer employeeId) throws SQLException, RemoteException {

        Employee emp = new Employee();
        String sql = "SELECT id, firstname, middlename, lastname, email, phone FROM employee;";

        PreparedStatement pStmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pStmt.executeQuery(sql);

        while (rs.next()) {
            if (employeeId == rs.getInt(1)) {
                rs.next();
                emp.setId(rs.getInt(1));
                emp.setFirstName(rs.getString(2));
                emp.setMiddleName(rs.getString(3));
                emp.setLastName(rs.getString(4));
                emp.setEmail(rs.getString(5));
                emp.setPhone(rs.getString(6));
            }
        }
        return emp;
    }

    @Override
    public List<Employee> selectAll() throws SQLException, RemoteException {
        List<Employee> emps = new ArrayList<>();
        String sql = "SELECT id, firstname, middlename, lastname, email, phone FROM employee;";


        PreparedStatement pStmt = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pStmt.executeQuery(sql);

        rs.beforeFirst();

        while (rs.next()) {
            Employee emp = new Employee();
            emp.setId(rs.getInt(1));
            emp.setFirstName(rs.getString(2));
            emp.setMiddleName(rs.getString(3));
            emp.setLastName(rs.getString(4));
            emp.setEmail(rs.getString(5));
            emp.setPhone(rs.getString(6));
            emps.add(emp);

        }
        return emps;

    }
}
