package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.exception.BookException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{
    private List<Book> BookList = new ArrayList<>();
    @Autowired
    BookRepository bookRepository;
    @Override
    public List<Book> getBookData() {
        return bookRepository.findAll();
    }
    @Override
    public Book getBookDataById(int Id) {
        return bookRepository.findById(Id).orElseThrow(() -> new BookException("Book Id With " +
                Id + " Does Not Exist"));
    }
    @Override
    public Book createBookData(BookDTO bookDTO){
        Book bookData = new Book(bookDTO);
        return bookRepository.save(bookData);
    }
    @Override
    public Book updateBookData(int Id, BookDTO bookDTO){
        if (bookRepository.findById(Id).isPresent()){
            Book bookDetails1=new Book(Id,bookDTO);
            bookRepository.save(bookDetails1);
            return bookDetails1;
        }
        else {
            throw new UserException("Id not found ");
        }
    }
    @Override
    public String deleteBookData(int ID) {
        Optional<Book> book = bookRepository.findById(ID);
        if (book.isPresent()){
            bookRepository.deleteById(ID);
            return "Data Deleted";
        }
        throw new BookException("Book id " + ID +" is not found");
    }
    @Override
    public List<Book> findByBook(String book){
        List<Book> bookList =  bookRepository.findByBook(book);
        if(bookList.isEmpty()) {
            throw new BookException("Book Store with Book Name "+ book + " not found!");
        }
        return  bookList;
    }

    @Override
    public List<Book> findBookQuantity() {
        return bookRepository.findAllOrderByQuantityAsc();
    }

    @Override
    public List<Book> findBookPriceHighToLow() {
        return bookRepository.findAllOrderByPriceDesc();
    }

    @Override
    public List<Book> findBookPriceLowToHigh() {
        return bookRepository.findAllOrderByPriceAsc();
    }

    @Override
    public Book updateBookQtyData(int id, BookDTO bookDTO){
        Book bookData = bookRepository.findById(id).orElse(null);
        if (bookData != null) {
            bookData.setQty(bookDTO.getQty());
            return bookRepository.save(bookData);
        }else return null;
    }
}
