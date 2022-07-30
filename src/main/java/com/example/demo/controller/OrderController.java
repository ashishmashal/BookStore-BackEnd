package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Order;
import com.example.demo.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    @PostMapping("/createorder/{token}")
    public ResponseEntity<ResponseDTO> insertOrderItem(@PathVariable String token,@RequestBody OrderDTO orderDTO) {
        Order newOrders = iOrderService.insertOrderItem(orderDTO,token);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newOrders);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<ResponseDTO> getAllOrderItems() {
        List<Order> newOrders = iOrderService.getAllOrderItems();
        ResponseDTO responseDTO = new ResponseDTO("All records retrieved successfully !", newOrders);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getOrder/{orderID}")
    public ResponseEntity<ResponseDTO> getOrderItems(@PathVariable Integer orderID) {
        Order newOrders = iOrderService.getOrderItems(orderID);
        ResponseDTO responseDTO = new ResponseDTO("Record retrieved successfully !", newOrders);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/deleteOrder/{orderID}")
    public ResponseEntity<ResponseDTO> deleteOrderItem(@PathVariable int orderID) {
        iOrderService.CancleOrderItem(orderID);
        ResponseDTO responseDTO = new ResponseDTO("Order Canceled successfully !", "Order Cancled " +orderID);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
}
