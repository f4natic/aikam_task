package com.example.service;

import com.example.model.Customer;
import com.example.model.Product;
import com.example.repository.Repository;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class Service {

    private Repository repository;

    public Service(SessionFactory sessionFactory) {
        this.repository= new Repository(sessionFactory);
    }

    public List<Customer> getCatomersBySurname(String surname) {
        try {

        }catch (HibernateException e) {

        }
        return null;
    }

    public Product getProductPurchasedTimes(String name, int times) {
        return null;
    }

    public List<Customer> getCatomersWithinRange(double min, double max) {
        return null;
    }

    public List<Customer> getBadCustomers(int size) {
        return null;
    }

    public List<Customer> getCustomersByStat(LocalDate beginDate, LocalDate endDate) {
        return null;
    }
}
