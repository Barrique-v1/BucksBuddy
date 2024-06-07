package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.expenditure.Expenditure;
import com.bucksbuddy.bucksbuddy.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Currency;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "journeys")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String HomeCurr;

    @Column(nullable = false)
    private String VacCurr;


    @Column(nullable = false)
    private int Budget;

    @Column(nullable = false)
    private Date StartDate;

    @Column(nullable = false)
    private Date EndDate;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "journey", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Expenditure> expenditures;

    public Journey() {
    }

    public Journey(String name, User user, String HomeCurr, String VacCurr, int Budget, Date StartDate, Date EndDate) {
        this.name = name;
        this.user = user;
        this.HomeCurr = HomeCurr;
        this.VacCurr = VacCurr;
        this.Budget = Budget;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String destination) {
        this.name = destination;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurr() {
        return HomeCurr;
    }

    public void setCurr(String HomeCurr) {
        this.HomeCurr = HomeCurr;
    }

    public String getHomeCurr() {
        return HomeCurr;
    }

    public void setHomeCurr(String homeCurr) {
        HomeCurr = homeCurr;
    }

    public String getVacCurr() {
        return VacCurr;
    }

    public void setVacCurr(String vacCurr) {
        VacCurr = vacCurr;
    }

    public int getBudget() {
        return Budget;
    }

    public void setBudget(int budget) {
        Budget = budget;
    }

    public String getStartDate() {
        return StartDate.toString();
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate.toString();
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
}
