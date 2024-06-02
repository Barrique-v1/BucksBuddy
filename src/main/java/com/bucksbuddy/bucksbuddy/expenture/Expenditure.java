package com.bucksbuddy.bucksbuddy.expenture;

import com.bucksbuddy.bucksbuddy.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "user_id") // Dies ist die Fremdschlüssel-Spalte
    @JsonBackReference
    private User user;

    public Expenditure() {
        this.date = new Date(); // Initialisiere das Datum mit dem aktuellen Datum
    }

    public Expenditure(String name, double amount, User user) {
        this.name = name;
        this.amount = amount;
        this.date = new Date();
        this.user = user;
    }

    // Getter und Setter...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
