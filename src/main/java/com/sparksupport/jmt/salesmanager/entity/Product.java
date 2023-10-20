package com.sparksupport.jmt.salesmanager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Field should not be empty")
    @Size(max = 20, message = "size must not exceed 20 letters,")
    private String name;
    @Size(max = 50, message = "size must not exceed 50 letters,")
    private String description;


    @Positive(message = "Value should be positive")
    private double price;
    @Positive(message = "Value should be positive")
    private int quantity;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<Sale> sales;

    public Product() {
    }

    public Product(Long id, String name, String description, double price, int quantity, List<Sale> sales) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.sales = sales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
