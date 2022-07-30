package com.example.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "orderDetails")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;
    private double price;
    @org.hibernate.annotations.ForeignKey(name = "none")
    @OneToMany(fetch = FetchType.LAZY,orphanRemoval=true)
    private List<Cart> cart;
    private int userId;
    private LocalDate date;
    private boolean cancel;
    private int quantity;
    private String address;
    @ManyToMany
    private List<Book> book;

    public Order(int userId, String address, List<Book> books, List<Cart> cart, double total_price, int quantity, boolean cancel) {
        this.userId=userId;
        this.cart=cart;
        this.date=LocalDate.now();
        this.price=total_price;
        this.cancel=cancel;
        this.quantity=quantity;
        this.address=address;
        this.book=books;
    }
}
