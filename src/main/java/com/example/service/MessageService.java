package com.example.service;

import com.example.criteria.Criteria;
import com.example.criteria.CriteriaResult;
import com.example.message.InputMessage;
import com.example.message.OutputMessage;
import com.example.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageService {
    private String type;
    private Service service;
    private InputMessage inputMessage;
    private FileService fileService;
    private ObjectMapper mapper;

    public MessageService(String type, Service service, InputMessage inputMessage, FileService fileService, ObjectMapper mapper) {
        this.type = type;
        this.service = service;
        this.inputMessage = inputMessage;
        this.fileService = fileService;
        this.mapper = mapper;
    }

    public void run() throws Exception {
        List<Criteria> criterias = inputMessage.getCriterias();
        OutputMessage outputMessage = new OutputMessage();
        outputMessage.setType(type);
        outputMessage.setResults(new ArrayList<>());

        if(type.equals("search")) {
            for(Criteria c : criterias) {
                if(c.getLastName() != null) {
                    CriteriaResult result = new CriteriaResult();
                    result.setCriteria(c);
                    result.setResults(service.getCatomersBySurname(c.getLastName()));
                    outputMessage.getResults().add(result);
                }
                if(c.getProductName() != null) {
                    CriteriaResult result = new CriteriaResult();
                    result.setCriteria(c);
                    result.setResults(service.getProductPurchasedTimes(c.getProductName(), c.getMinTimes()));
                    outputMessage.getResults().add(result);
                }
                if(c.getMinExpenses() != null) {
                    CriteriaResult result = new CriteriaResult();
                    result.setCriteria(c);
                    result.setResults(service.getCatomersWithinRange(c.getMinExpenses(), c.getMaxExpenses()));
                    outputMessage.getResults().add(result);
                }

                if(c.getBadCustomers() != null) {
                    CriteriaResult result = new CriteriaResult();
                    result.setCriteria(c);
                    result.setResults(service.getBadCustomers(c.getBadCustomers()));
                    outputMessage.getResults().add(result);
                }
            }
        }
        if(type.equals("stat")) {
            if(inputMessage.getStartDate().isAfter(inputMessage.getEndDate())) {
                throw new Exception("дата начала поиска не может быть больше даты окончания поиска...");
            }

            long numDate = inputMessage.getEndDate().toEpochDay()-inputMessage.getStartDate().toEpochDay();

            List<Customer> customers = service.getCustomersByStat(inputMessage.getStartDate(), inputMessage.getEndDate());
        }

        fileService.writeFile(mapper.writeValueAsString(outputMessage));
    }
}
