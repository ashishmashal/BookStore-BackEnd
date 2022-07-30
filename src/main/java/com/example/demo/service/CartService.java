package com.example.demo.service;

import com.example.demo.dto.CartDTO;
import com.example.demo.exception.BookException;
import com.example.demo.exception.CartException;
import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CartService implements ICartService{

    @Autowired
    CartRepository cartRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public Cart insertItems(CartDTO cartDTO,String token) throws BookException {
        int id = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(id);
        Optional<Book> bookDetails = bookRepository.findById(cartDTO.getBookId());
        Cart cartDetails;
        if (user.isPresent() && bookDetails.isPresent()) {
            if (cartDTO.getQty() > 0 && bookDetails.get().getQty() >= cartDTO.getQty()) {
                Cart cartDetails1 = cartRepository.findByUserId(id,cartDTO.getBookId());
                if (cartDetails1 != null) {
                    cartDetails = updateCarts(cartDetails1.getCartID(), cartDTO);
                    return cartDetails;
                } else {
                    cartDetails = new Cart(user.get(),bookDetails.get(),cartDTO.getQty());
                    return cartRepository.save(cartDetails);
                }
            } else {
                throw new BookException("Book Quantity is not available as per order !!");
            }
        } else {
            throw new BookException("Book Or User Might be not Present !!!");
        }
    }

    private Cart updateCarts(int id, CartDTO cartDTO) {
        if (cartRepository.findById(id).isPresent()) {
            Optional<Cart> cartDetails = cartRepository.findById(id);
            cartDetails.get().setQty(cartDetails.get().getQty() + cartDTO.getQty());
            cartDetails.get().setTotalPrice(cartDetails.get().calculatePrice());
            return cartRepository.save(cartDetails.get());
        } else {
            throw new BookException("Record not available of provided id");
        }
    }

    @Override
    public List<Cart> getAllCartItems() {
        List<Cart> cart = cartRepository.findAll();
        return cart;
    }

    @Override
    public Cart getCartRecordById(Integer cartID) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        if (cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        } else {
            log.info("Cart record retrieved successfully for id " + cartID);
            return cart.get();
        }
    }
    @Override
    public Cart updateCartItems(Integer cartID, CartDTO cartdto) {
        String token = null;
        int id = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(id);
        Optional<Book> bookDetails = bookRepository.findById(cartdto.getBookId());
        if (cartRepository.findById(cartID).isPresent()) {
            if (user.isPresent()) {
                if (bookDetails.isPresent()) {
                    Optional<Cart> cartDetails = cartRepository.findById(cartID);
                    cartDetails.get().setQty(cartdto.getQty());
                    cartDetails.get().setTotalPrice(cartDetails.get().calculatePrice());
                    cartRepository.save(cartDetails.get());
                    return cartDetails.get();
                }  throw new CartException("Book ID Not Found");
            }throw new CartException("User Id Not Found");
        }
        throw new BookException("Record not available of provided id");
    }

    @Override
    public Cart deleteCartItems(Integer cartID) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        Optional<Book> book = bookRepository.findById(cart.get().getBookData().getId());
        if (cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        } else {
            book.get().setQty(book.get().getQty() + cart.get().getQty());
            bookRepository.save(book.get());
            cartRepository.deleteById(cartID);
            log.info("Cart record deleted successfully for id " + cartID);
            return cart.get();
        }
    }

    @Override
    public Cart updateQuantityItems(Integer cartID, Integer quantity) {
        Optional<Cart> cart = cartRepository.findById(cartID);
        Optional<Book> book = bookRepository.findById(cart.get().getBookData().getId());
        if (cart.isEmpty()) {
            throw new CartException("Cart Record doesn't exists");
        } else {
            if (quantity < book.get().getQty()) {
                cart.get().setQty(quantity);
                cartRepository.save(cart.get());
                log.info("Quantity in cart record updated successfully");
                book.get().setQty(book.get().getQty() - (quantity - cart.get().getQty()));
                bookRepository.save(book.get());
                return cart.get();
            } else {
                throw new CartException("Requested quantity is not available");
            }
        }
    }

//    @Override
//    public List<Cart> getCartItemByUserId(int id) {
//        List<Cart> cartList = cartRepository.getCartsByUserId(id);
//        if (cartList.isEmpty())
//            throw new CustomException("Cart with User id "+id+" not found!");
//        return cartList;
//    }
}
