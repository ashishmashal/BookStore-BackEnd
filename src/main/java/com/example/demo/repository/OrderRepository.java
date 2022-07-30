package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update bookstore.order_details set cancel=true where orderid=:id", nativeQuery = true)
    int updateCancel(int id);



    @Query(value = "SELECT * FROM bookstore.order_details where orderid = :id",nativeQuery = true)
    Order findByOrderId(int id);
}
