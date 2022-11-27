package com.example.service;

import com.example.message.ErrorMessage;
import com.example.model.*;
import com.example.repository.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Service {

    @Setter
    private Repository repository;
    private FileService fileService;
    private ObjectMapper mapper;

    public Service(SessionFactory sessionFactory, FileService fileService, ObjectMapper mapper) {
        this.repository= new Repository(sessionFactory);
    }

    public List<Customer> getCatomersBySurname(String surname) throws IOException {
        List<Customer> customers = null;
        try {
            customers = repository.getCatomersBySurname(surname);

        }catch (HibernateException e) {
            fileService.writeFile(mapper.writeValueAsString(new ErrorMessage(e.getMessage())));
        }
        return customers;
    }

    public List<Customer> getProductPurchasedTimes(String name, int times) throws IOException {
        Product product = null;
        try {
            product = repository.getProductPurchasedTimes(name, times);

        }catch (HibernateException e) {
            fileService.writeFile(mapper.writeValueAsString(new ErrorMessage(e.getMessage())));
        }
        return product.getCustomers();
    }

    public List<Customer> getCatomersWithinRange(double min, double max) throws IOException {
        List<Customer> customers = null;
        try {
            customers = repository.getCatomersWithinRange(min, max);

        }catch (HibernateException e) {
            fileService.writeFile(mapper.writeValueAsString(new ErrorMessage(e.getMessage())));
        }
        return customers;
    }

    public List<Customer> getBadCustomers(int size) throws IOException {
        List<Customer> customers = null;
        try {
            customers = repository.getBadCustomers(size);

        }catch (HibernateException e) {
            fileService.writeFile(mapper.writeValueAsString(new ErrorMessage(e.getMessage())));
        }
        return customers;
    }

    // Решение не очень оптимальное и красивое, можно подумать над сущностями и заставить БД это все делать
    public List<StatResult> getCustomersByStat(LocalDate beginDate, LocalDate endDate) throws IOException {
        List<Result> results = null;
        List<StatResult> statResultList = new ArrayList<>();
        try {
            results = repository.getCustomersByStat(beginDate, endDate);
            Map<String, Set<Purchases>> transformCustomer = new HashMap<>();
            for(Result r: results) {
                if(!transformCustomer.containsKey(r.getCustomer().getSurname() + " " + r.getCustomer().getName())) {
                    transformCustomer.put(r.getCustomer().getSurname() + " " + r.getCustomer().getName(),new HashSet<>());
                    transformCustomer.get(r.getCustomer().getSurname() + " " + r.getCustomer().getName()).add(new Purchases(r.getProductName(), r.getTotal()));
                }else {
                    transformCustomer.get(r.getCustomer().getSurname() + " " + r.getCustomer().getName()).add(new Purchases(r.getProductName(), r.getTotal()));
                }
            }

            for(String key : transformCustomer.keySet()) {
                StatResult res = new StatResult();
                res.setName(key);
                res.setPurchases(transformCustomer.get(key));
                statResultList.add(res);
            }

            for(StatResult sr:statResultList) {
                sr.countTotalExpenses();
            }

        }catch (HibernateException e) {
            fileService.writeFile(mapper.writeValueAsString(new ErrorMessage(e.getMessage())));
        }
        return statResultList;
    }
}
