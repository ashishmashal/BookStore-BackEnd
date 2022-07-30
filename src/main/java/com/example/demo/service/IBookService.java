package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookService {
    List<Book> getBookData();

    Book getBookDataById(int empId);

    Book createBookData(BookDTO bookDTO);

    Book updateBookData(int Id, BookDTO bookDTO);

    String deleteBookData(int ID);

    List<Book> findByBook(String city);


   List<Book> findBookQuantity();

    List<Book> findBookPriceHighToLow();

    List<Book> findBookPriceLowToHigh();

//    Book updateBookQtyData(String bookName, BookDTO bookDTO);

    Book updateBookQtyData(int id, BookDTO bookDTO);
}
