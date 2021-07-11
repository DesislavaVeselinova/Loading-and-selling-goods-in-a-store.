package com.company;

public class Cashier {
    private String cashierName;
    private long cashierID;
    private double salary;

    public Cashier(String cashierName, long cashierID, double salary) {
        this.cashierName = cashierName;
        this.cashierID = cashierID;
        this.salary = salary;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public long getCashierID() {
        return cashierID;
    }

    public void setCashierID(long cashierID) {
        this.cashierID = cashierID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
