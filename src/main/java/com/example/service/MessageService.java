package com.example.service;

import com.example.criteria.Criteria;
import com.example.criteria.CriteriaResult;
import com.example.message.InputMessage;
import com.example.message.OutputMessage;
import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.StatResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        if(type.equals("search")) {
            outputMessage.setResults(new ArrayList<>());
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
            outputMessage.setType(type);
            long totalDate = inputMessage.getEndDate().toEpochDay()-inputMessage.getStartDate().toEpochDay();
            outputMessage.setTotalDays(totalDate);

            List<StatResult> customers = service.getCustomersByStat(inputMessage.getStartDate(), inputMessage.getEndDate());
            outputMessage.setCustomers(customers);
            double totalExpenses = 0;
            long purchasesTotalSize = 0;
            for(StatResult sr:customers) {
                purchasesTotalSize +=sr.getPurchases().size();
                totalExpenses += sr.getTotalExpenses();
            }
            double avg = totalExpenses / purchasesTotalSize;
            BigDecimal bd = new BigDecimal(avg).setScale(2, RoundingMode.FLOOR);
            outputMessage.setTotalExpenses(totalExpenses);
            outputMessage.setAvgExpenses(bd.doubleValue());
        }

        fileService.writeFile(mapper.writeValueAsString(outputMessage));
    }
}
