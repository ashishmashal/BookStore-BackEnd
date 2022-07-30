package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Cart;
import com.example.demo.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    ICartService iCartService;

    @PostMapping("/addInCart/{token}")
    public ResponseEntity<ResponseDTO> insertItem(@PathVariable String token,@RequestBody CartDTO cartdto) {
        Cart newCart = iCartService.insertItems(cartdto,token);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/allcartvalues")
    public ResponseEntity<ResponseDTO> getAllCartItems() {
        List<Cart> newCart = iCartService.getAllCartItems();
        ResponseDTO responseDTO = new ResponseDTO("All records retrieved successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{cartID}")
    public ResponseEntity<ResponseDTO> getCartRecord(@PathVariable Integer cartID) {
        Cart newCart = iCartService.getCartRecordById(cartID);
        ResponseDTO responseDTO = new ResponseDTO("Record retrieved successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }


    @PutMapping("/update/{cartID}")
    public ResponseEntity<ResponseDTO> updateCartItems(@PathVariable Integer cartID, @Valid @RequestBody CartDTO cartdto) {
        Cart newCart = iCartService.updateCartItems(cartID, cartdto);
        ResponseDTO responseDTO = new ResponseDTO("Record updated successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteCart/{cartID}")
    public ResponseEntity<ResponseDTO> deleteCartItems(@PathVariable Integer cartID) {
        Cart newCart = iCartService.deleteCartItems(cartID);
        ResponseDTO responseDTO = new ResponseDTO("Record deleted successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateQuantity/{cartID}/{quantity}")
    public ResponseEntity<ResponseDTO> updateQuantityItems(@PathVariable Integer cartID, @PathVariable Integer quantity) {
        Cart newCart = iCartService.updateQuantityItems(cartID, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Quantity for book record updated successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
}
