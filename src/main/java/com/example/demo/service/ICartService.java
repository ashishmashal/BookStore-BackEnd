package com.example.demo.service;

import com.example.demo.dto.CartDTO;
import com.example.demo.exception.BookException;
import com.example.demo.model.Cart;
import com.example.demo.model.User;

import java.util.List;

public interface ICartService {

    Cart insertItems(CartDTO cartDTO, String token) throws BookException;

    List<Cart> getAllCartItems();

    Cart getCartRecordById(Integer cartID);

    Cart updateCartItems(Integer cartID, CartDTO cartdto);

    Cart deleteCartItems(Integer cartID);

    Cart updateQuantityItems(Integer cartID, Integer quantity);

   // List<Cart> getCartItemByUserId(int id);
}
