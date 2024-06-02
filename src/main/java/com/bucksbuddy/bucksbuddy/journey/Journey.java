//package com.bucksbuddy.bucksbuddy.journey;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "journey")
//public class Journey {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//
//
//    private String user_fk;
//    private double amount;
//    private String homeCurrency;
//    private String foreignCurrency;
//    //TODO: hier private double exchangeRate; ?
//
//    public Journey() {
//    }
//
//    public Journey(int id, String user_fk, double amount, String homeCurrency, String foreignCurrency) {
//        this.id = id;
//        this.user_fk = user_fk;
//        this.amount = amount;
//        this.homeCurrency = homeCurrency;
//        this.foreignCurrency = foreignCurrency;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUser_fk() {
//        return user_fk;
//    }
//
//    public void setUser_fk(String user_fk) {
//        this.user_fk = user_fk;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public String getHomeCurrency() {
//        return homeCurrency;
//    }
//
//    public void setHomeCurrency(String homeCurrency) {
//        this.homeCurrency = homeCurrency;
//    }
//
//    public String getForeignCurrency() {
//        return foreignCurrency;
//    }
//
//    public void setForeignCurrency(String foreignCurrency) {
//        this.foreignCurrency = foreignCurrency;
//    }
//}
