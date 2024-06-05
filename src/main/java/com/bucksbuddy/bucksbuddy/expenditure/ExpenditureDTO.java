package com.bucksbuddy.bucksbuddy.expenditure;

import com.bucksbuddy.bucksbuddy.journey.Journey;

import java.util.Date;

public class ExpenditureDTO {
    private String name;
    private double amount;
    private Date date;
    private int journeyId;



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

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourney(int journeyId) {
        this.journeyId = journeyId;
    }

}

