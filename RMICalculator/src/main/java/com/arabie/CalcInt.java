package com.arabie;

import java.rmi.Remote;
import java.rmi.RemoteException;
//(add, subtract, multiply, divide)
public interface CalcInt extends Remote {

    Double add(Double operand1,Double operand2) throws RemoteException;
    Double subtract(Double operand1,Double operand2) throws RemoteException;
    Double multiply(Double operand1,Double operand2) throws RemoteException;
    Double divide(Double operand1,Double operand2) throws RemoteException;
}
