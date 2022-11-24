package com.example.service;

import com.example.message.ErrorMessage;
import com.example.model.Customer;
import com.example.model.Product;
import com.example.repository.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Service {

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

    public List<Customer> getCustomersByStat(LocalDate beginDate, LocalDate endDate) throws IOException {
        List<Customer> customers = null;
        try {
            customers = repository.getCustomersByStat(beginDate, endDate);

        }catch (HibernateException e) {
            fileService.writeFile(mapper.writeValueAsString(new ErrorMessage(e.getMessage())));
        }
        return customers;
    }
}
