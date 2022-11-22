package com.example.model;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Customer")
@Table(name="customers")
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @ManyToMany
    @JoinTable(
            name = "purchases",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Customer(Long id, String name, String surname) {
        this.id=id;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return String.format("Customer [id = %d, name = %s, surname = %s", id, name, surname);
    }
}
