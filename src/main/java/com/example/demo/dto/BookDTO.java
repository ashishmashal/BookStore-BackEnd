package com.example.demo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class BookDTO {
    @NotNull(message = "Book Name Cannot Be Null")
    public String bookName;
    @NotNull(message = "Author Name Cannot Be Null")
    public String authorName;
    @NotNull(message = "Book Description Cannot Be Null")
    public String bookDescription;
    @NotNull(message = "Book Image Cannot Be Null")
    public String bookImg;
    @NotNull(message = "Price Cannot Be Null")
    public int price;
    @NotNull(message = "Quantity Cannot Be Null")
    public int qty;
}
