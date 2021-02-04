package com.arabie;

import java.io.Serializable;

public class MyClass implements Serializable {

    private String myStr;
    private int myInt;
    private double myDouble;

    public MyClass(String myStr, int myInt, double myDouble) {
        this.myStr = myStr;
        this.myInt = myInt;
        this.myDouble = myDouble;
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "myStr='" + myStr + '\'' +
                ", myInt=" + myInt +
                ", myDouble=" + myDouble +
                '}';
    }

    public String getMyStr() {
        return myStr;
    }

    public void setMyStr(String myStr) {
        this.myStr = myStr;
    }

    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }

    public double getMyDouble() {
        return myDouble;
    }

    public void setMyDouble(double myDouble) {
        this.myDouble = myDouble;
    }
}
