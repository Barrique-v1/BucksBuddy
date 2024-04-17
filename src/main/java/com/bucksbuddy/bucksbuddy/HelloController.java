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
        Expenditures expenditure1 = new Expenditures("Kartoffeln", 12.02, "Peter");
        Expenditures expenditure2 = new Expenditures("Kartoffeln", 12.02, "Peter");
        Expenditures expenditure3 = new Expenditures("Kartoffeln", 12.02, "Peter");
        Expenditures expenditure4 = new Expenditures("Kartoffeln", 12.02, "Peter");
        return List.of(expenditure1,expenditure2,expenditure3,expenditure4);
    }
}
