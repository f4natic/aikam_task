package com.example.message;

import com.example.criteria.Criteria;
import lombok.Data;

import java.util.List;

@Data
public class InputMessage {
    private List<Criteria> criterias;
}
