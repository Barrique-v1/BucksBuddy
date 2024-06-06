package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.expenditure.Expenditure;
import com.bucksbuddy.bucksbuddy.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Currency;
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
    private String curr;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "journey", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Expenditure> expenditures;

    public Journey() {
    }

    public Journey(String name, User user, Set<Expenditure> expenditures, String curr) {
        this.name = name;
        this.user = user;
        this.expenditures = expenditures;
        this.curr = curr;
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

    public Set<Expenditure> getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(Set<Expenditure> expenditures) {
        this.expenditures = expenditures;


    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }
}
