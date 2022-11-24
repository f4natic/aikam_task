package com.example.message;

import com.example.criteria.CriteriaResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputMessage {
    private String type;
    private Long totalDays;
    private List<CriteriaResult> results;
}
