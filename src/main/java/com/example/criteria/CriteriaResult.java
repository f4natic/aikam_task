package com.example.criteria;

import com.example.model.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CriteriaResult {
    private Criteria criteria;
    private List<Customer> results;
}
