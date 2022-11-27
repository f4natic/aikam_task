package com.example.message;

import com.example.criteria.CriteriaResult;
import com.example.model.StatResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputMessage {
    private String type = null;
    private Long totalDays = null;
    private List<CriteriaResult> results = null;
    private List<StatResult> customers = null;
    private Double totalExpenses = null;
    private Double avgExpenses = null;
}
