package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Order;

import java.util.ArrayList;
import java.util.List;

public interface IOrderService {
    Order insertOrderItem(OrderDTO orderDTO,String token);

    List<Order> getAllOrderItems();

    Order getOrderItems(Integer orderID);


    void CancleOrderItem(int orderID);


}
