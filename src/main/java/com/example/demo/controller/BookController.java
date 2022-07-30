package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Book;
import com.example.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    IBookService iBookService;

    List<Book> bookDataList = new ArrayList<>();
    
    @RequestMapping(value = {"","/", "/getBook"})
    public ResponseEntity<ResponseDTO> getBookData() {
        bookDataList = iBookService.getBookData();
        ResponseDTO respOTO = new ResponseDTO("Get Call Successful", bookDataList);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }
    @GetMapping("/getBookByID/{Id}")
    public ResponseEntity<ResponseDTO> getBookData(@PathVariable("Id") int ID) {
        Book empData= null;
        empData = iBookService.getBookDataById(ID);
        ResponseDTO respDTO= new ResponseDTO("Get Call For ID Successful", empData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }
    @PostMapping("/addBook")
    public ResponseEntity<ResponseDTO> createBookData(
            @Valid @RequestBody BookDTO bookDTO) {
        Book empData = iBookService.createBookData(bookDTO);
        ResponseDTO respOTO= new ResponseDTO("Created Book  Data Successfully", empData);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable int id,@Valid @RequestBody BookDTO bookDTO) {
        ResponseDTO respDTO= new ResponseDTO("Updated Book Details Successfully", iBookService.updateBookData(id,bookDTO));
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{Id}")
    public ResponseEntity <ResponseDTO> deleteBookData(@PathVariable("Id") int Id) {
        iBookService.deleteBookData(Id);
        ResponseDTO respDTO= new ResponseDTO("Deleted Successfully", "Deleted id: "+Id);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/bookname/{bookName}")
    public ResponseEntity<ResponseDTO> getBookNameData(@PathVariable("bookName") String bookName) {
        List<Book> bookDataList = iBookService.findByBook(bookName);
        ResponseDTO respOTO= new ResponseDTO("Get Call For ID Successful", bookDataList);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }
    @GetMapping(value = "/sortQuantity")
    public List<Book> findBookInAscendingOrder() {
        return iBookService.findBookQuantity();
    }

    @GetMapping(value = "/sortPriceHigh")
    public List<Book> findBookPriceInAscendingOrder() {
        return iBookService.findBookPriceHighToLow();
    }

    @GetMapping(value = "/sortPriceLow")
    public List<Book> findBookPriceLowToHigh() {
        return iBookService.findBookPriceLowToHigh();
    }

    @PatchMapping("/updateQty/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookQtyData(@PathVariable int bookId,@RequestBody BookDTO bookDTO) {
        ResponseDTO respDTO= new ResponseDTO("Updated Book Details Successfully", iBookService.updateBookQtyData(bookId,bookDTO));
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

}
