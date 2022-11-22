package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity(name="Purchase")
@Table(name="purchases")
@NoArgsConstructor
public class Purchase {

    @EmbeddedId
    private PurchaseId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customersId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productsId")
    private Product product;

    @Column(name = "date")
    private Timestamp date;

    public Purchase(Customer customer, Product product) {
        this.customer=customer;
        this.product = product;
        this.id = new PurchaseId(customer.getId(), product.getId());
    }

    public Purchase(Customer customer, Product product, Timestamp date) {
        this.customer=customer;
        this.product = product;
        this.date = date;
        this.id = new PurchaseId(customer.getId(), product.getId());
    }
}
