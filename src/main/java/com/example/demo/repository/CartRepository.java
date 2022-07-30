package com.example.demo.repository;

import com.example.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "SELECT * FROM bookstore.cart where user_id = :user_id AND book_id = :book_id",nativeQuery = true)
    Cart findByUserId(int user_id,int book_id);

    @Query(value = "SELECT * FROM bookstore.cart where user_id =:id",nativeQuery = true)
    List<Cart> findByUser(int id);

//    @Query(value = "select * from cart where user_id=:id", nativeQuery = true)
//    List<Cart> getCartsByUserId(int id);
}

/*
* <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.18.3</version>
        </dependency>
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
* */
