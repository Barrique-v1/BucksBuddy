package com.bucksbuddy.bucksbuddy.expenditure;

import java.util.Date;

public class ExpenditureDTO {
    private String name;
    private double amount;
    private Date date;

    // Getter und Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

