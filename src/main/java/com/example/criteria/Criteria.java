package com.example.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Criteria {

    private String lastName;
    private String productName;
    private Integer minTimes;
    private Double minExpenses;
    private Double maxExpenses;
    private Integer badCustomers;

}
