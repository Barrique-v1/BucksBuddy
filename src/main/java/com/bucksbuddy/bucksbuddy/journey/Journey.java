package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.expenditure.Expenditure;
import com.bucksbuddy.bucksbuddy.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "journeys")
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destination;

    // später hinzufügen wenn's läuft
    //    private String homeCurrency;
    //    private String foreignCurrency;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "journey", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Expenditure> expenditures = new HashSet<>();

    public Journey() {
    }

    public Journey(String destination, User user) {
        this.destination = destination;
        this.user = user;
    }

    // Getter und Setter...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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
}
