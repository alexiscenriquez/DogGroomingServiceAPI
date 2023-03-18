package com.myprojects.dogs.models;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Dog_Customer implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
            @JoinColumn(name="dog_id",referencedColumnName = "id")
    Dogs dog;
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    Customer customer;

    public Dog_Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dogs getDogs() {
        return dog;
    }

    public void setDogs(Dogs dogs) {
        this.dog = dogs;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Dog_Customer{" +
                "id=" + id +
                ", dogs=" + dog +
                ", customer=" + customer +
                '}';
    }
}
