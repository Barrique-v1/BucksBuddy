package com.bucksbuddy.bucksbuddy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/expenses")
    public List<Expenditures> getExpenses() {
        Expenditures expenditure1 = new Expenditures( 1,"Kartoffeln", 12.02, "Peter", "EUR");
        Expenditures expenditure2 = new Expenditures(2,"Kartoffeln", 12.02, "Peter", "EUR");
        Expenditures expenditure3 = new Expenditures(3,"Kartoffeln", 12.02, "Peter", "EUR");
        Expenditures expenditure4 = new Expenditures(4,"Kartoffeln", 12.02, "Peter", "EUR");
        return List.of(expenditure1,expenditure2,expenditure3,expenditure4);
    }
}
