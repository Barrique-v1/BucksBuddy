package com.bucksbuddy.bucksbuddy.user;

import com.bucksbuddy.bucksbuddy.expenditure.Expenditure;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String uuid;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Expenditure> expenditures;

    public User() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.uuid = java.util.UUID.randomUUID().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<Expenditure> getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(Set<Expenditure> expenditures) {
        this.expenditures = expenditures;
    }
}
