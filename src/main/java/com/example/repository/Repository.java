package com.example.repository;

import com.example.model.Customer;
import com.example.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class Repository {

    private SessionFactory sessionFactory;
    private Session session;

    public Repository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Customer> getCatomersBySurname(String surname) throws HibernateException {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Customer> customerList = session
                .createQuery("SELECT c FROM Customer  c WHERE c.surname=:surname", Customer.class)
                .setParameter("surname", surname)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return customerList;
    }

    public Product getProductPurchasedTimes(String name, int times) throws HibernateException {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session
                .createQuery("SELECT p FROM Product  p WHERE p.name=:name AND p.customers.size>=:times", Product.class)
                .setParameter("name", name)
                .setParameter("times", times)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public List<Customer> getCatomersWithinRange(double min, double max) throws HibernateException {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Customer> customerList = session
                .createQuery("SELECT cs FROM Customer cs where (select sum(pr.price) from Purchase pu, Product pr where pr.id = pu.product.id and cs.id=pu.customer.id) between :min and :max", Customer.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return customerList;
    }

    public List<Customer> getBadCustomers(int size) throws HibernateException {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Customer> badCustomers = session
                .createQuery("SELECT cs FROM Customer cs order by cs.products.size asc", Customer.class)
                .setMaxResults(size)
                .getResultList();
        session.getTransaction().commit();
        session.close();
        return badCustomers;
    }

    public List<Customer> getCustomersByStat(LocalDate beginDate, LocalDate endDate) throws HibernateException {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Customer> customerList = session
                .createQuery("SELECT cs FROM Customer cs, Purchase pc WHERE cs.id = pc.customer.id AND pc.date between :startDate AND :endDate", Customer.class)
                .setParameter("startDate", Timestamp.valueOf(beginDate.atStartOfDay()))
                .setParameter("endDate", Timestamp.valueOf(endDate.atStartOfDay()))
                .getResultList();
        session.getTransaction().commit();
        session.close();

        return customerList;
    }
}
