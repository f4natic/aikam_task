package com.example.message;

import com.example.criteria.CriteriaResult;
import lombok.Data;

import java.util.List;

@Data
public class OutputMessage {
    private String type;
    private List<CriteriaResult> results;
}
