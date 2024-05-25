package com.bucksbuddy.bucksbuddy.expenture;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expenditures")
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double amount;
    private Date date;

    public Expenditure() {
    }

    public Expenditure(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
