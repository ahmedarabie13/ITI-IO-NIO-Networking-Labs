package com.arabie;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcImpl extends UnicastRemoteObject implements CalcInt {
    public CalcImpl() throws RemoteException {
//super();
    }



    @Override
    public Double add(Double operand1, Double operand2) throws RemoteException {
        return operand1+operand2;
    }

    @Override
    public Double subtract(Double operand1, Double operand2) throws RemoteException {
        return operand1-operand2;
    }

    @Override
    public Double multiply(Double operand1, Double operand2) throws RemoteException {
        return operand1*operand2;
    }

    @Override
    public Double divide(Double operand1, Double operand2) throws RemoteException {
        return operand1/operand2;
    }
}