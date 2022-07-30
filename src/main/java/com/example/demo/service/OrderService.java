package com.example.demo.service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.exception.OrderException;
import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    IBookService iBookService;
   @Autowired
    TokenUtil tokenUtil;
    ArrayList<Order> orderList = new ArrayList<>();
    @Override
    public Order insertOrderItem(OrderDTO orderDTO,String token) {
        int id = tokenUtil.decodeToken(token);
        List<Cart> cart = cartRepository.findByUser(id);
        List<Book> books= new ArrayList<>();
        double total_price = 0;

        int quantity = 0;
        for (int i=0; i<cart.size(); i++) {
             quantity = quantity + cart.get(i).getQty();
             total_price = total_price + cart.get(i).getTotalPrice();
             books.add(cart.get(i).getBookData());
        }
        String address =null;
            if(orderDTO.getAddress()==null)
            {
                User user = iUserService.getUserDataById(token);
                address = user.getAddress();
            }else {
                address = orderDTO.getAddress();
            }

            Order order = new Order(id,address,books,cart, total_price,quantity,false);
            orderList.add(order);
            orderRepository.save(order);
        for (int i=0; i<cart.size(); i++) {
            Book book = cart.get(i).getBookData();
            int updatedQty = this.updateBookQty(book.getQty(), cart.get(i).getQty());
            book.setQty(updatedQty);
            cartRepository.deleteById(cart.get(i).getCartID());
        }
        return order;
    }
    private int updateBookQty(int bookQty, int bookQtyInCart) {
        int updatedQty = bookQty - bookQtyInCart;
        if (updatedQty <= 0)
            return 0;
        else
            return updatedQty;
    }

    @Override
    public List<Order> getAllOrderItems() {
        List<Order> orders = orderRepository.findAll();
        log.info(" Order Items get successfully");
        return orders;
    }

    @Override
    public Order getOrderItems(Integer orderID) {
        Optional<Order> orders = orderRepository.findById(orderID);
        if (orders.isEmpty()) {
            throw new OrderException("Order Item doesn't exists");
        } else {
            log.info("Order item get successfully for id " + orderID);
            return orders.get();
        }
    }
    @Override
    public void CancleOrderItem(int id) {
        Order order = this.getOrderItems(id);
        if (order.isCancel() == false) {
            orderRepository.updateCancel(id);
            List<Book> book = order.getBook();
            for (int j = 0; j < orderList.size(); j++) { //order ids
                if (orderList.get(j).getOrderID() == id) { //id is there or not
                    for (int i = 0; i < book.size(); i++) { // book size qty in order
                        Book updateBook = iBookService.getBookDataById(book.get(i).getId()); //wich to be update
                        for (int k = 0; k < book.size(); k++) {// cart
                            int orderedBookQty = orderList.get(j).getCart().get(k).getQty(); //from cart qty
                            int orderedBookId = orderList.get(j).getCart().get(k).getBookData().getId(); //book id
                            int bookId = updateBook.getId();
                            if (orderedBookId == bookId) {
                                int updatedQty = orderedBookQty + updateBook.getQty();
                                updateBook.setQty(updatedQty);
                                bookRepository.save(book.get(i));
                            }
                        }
                    }
                }
            }
        }
        else throw new OrderException("Order is already canceled!");
    }
}

