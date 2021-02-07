package com.arabie;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalcClient {
    public static void main(String[] args) {
        new CalcClient(args);
    }

    public CalcClient(String[] args) {
        try {
            Registry reg =
                    LocateRegistry.getRegistry(1875);
            CalcInt calculatorRef = (CalcInt)
                    reg.lookup("CalculatorRMI");
            Double operand1 =Double.parseDouble(args[0]);
            Double operand2 =Double.parseDouble(args[2]);
            Double result;

            switch (args[1]){
                case "+":
                    result=calculatorRef.add(operand1,operand2);
                    break;
                case "-":
                    result=calculatorRef.subtract(operand1,operand2);
                    break;
                case "*":
                    result=calculatorRef.multiply(operand1,operand2);
                    break;
                case "/":
                    result=calculatorRef.divide(operand1,operand2);
                    break;
                default:
                    result=null;
            }
            if(result!=null)
                System.out.println(operand1+" "+args[1]+" "+operand2+" = "+result);
            else
                System.out.println("Invalid operation");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}