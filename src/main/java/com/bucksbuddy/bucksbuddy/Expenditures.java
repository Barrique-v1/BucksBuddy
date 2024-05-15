package com.bucksbuddy.bucksbuddy;

import jakarta.persistence.*;

@Entity
@Table(name = "expenditures")
public class Expenditures {
    @Id
    private int id;
    private String name;
    private double amount;
    private String person;
    private String currency;

    public Expenditures(int id, String name, double amount, String person, String currency) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.person = person;
        this.currency = currency;

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
