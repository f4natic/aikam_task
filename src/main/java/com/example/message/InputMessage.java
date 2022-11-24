package com.example.message;

import com.example.criteria.Criteria;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InputMessage {
    private List<Criteria> criterias;

    private LocalDate startDate;
    private LocalDate endDate;
}
