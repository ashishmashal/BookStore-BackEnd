package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartDTO {
    private Integer bookId;
    @NotNull(message = "Quantity Cannot Be Null")
    private int qty;

}
