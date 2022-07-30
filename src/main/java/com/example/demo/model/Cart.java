package com.example.demo.model;

import com.example.demo.dto.CartDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@Entity
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private int cartID;
    private int qty;

    @OneToOne
    @JoinColumn(name = "userId")
    private User userdata;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book bookData;
    private double totalPrice;

    public Cart(){}

    public Cart(int quantity, Book book, User user) {
        this.qty = quantity;
        this.bookData = book;
        this.userdata = user;
        this.totalPrice=calculatePrice();
    }

//    public Cart(int cartID, Integer quantity, Book book, User user) {
//        //super();
//        this.cartID = cartID;
//        this.qty= quantity;
//        this.bookData = book;
//        this.userdata = user;
//    }


    public Cart(User user, Book book, int qty) {
        this.qty = qty;
        this.bookData = book;
        this.userdata = user;
        this.totalPrice = calculatePrice();
    }

    public double calculatePrice(){
        double totalPrice = this.getQty() * this.bookData.getPrice();
        return totalPrice;
    }
}
