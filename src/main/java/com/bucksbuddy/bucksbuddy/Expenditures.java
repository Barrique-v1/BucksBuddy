package com.bucksbuddy.bucksbuddy;

public class Expenditures {

    private String name;
    private double amount;
    private String person;

    public Expenditures(String name, double amount, String person) {
    this.name = name;
    this.amount = amount;
    this.person = person;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getPerson() {
        return person;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
