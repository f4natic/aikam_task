package com.example.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StatResult {
    private String name;
    private Set<Purchases> purchases;
    private Double totalExpenses;

    public StatResult() {
        purchases = new HashSet<>();
    }

    public void countTotalExpenses() {
        if(purchases.size() != 0) {
            totalExpenses = 0.0;
        }
        for(Purchases p: purchases) {
            totalExpenses += p.getExpenses();
        }
    }
}
