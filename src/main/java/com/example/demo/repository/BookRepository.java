package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "select * from book where id=id and book_name = :book_name",nativeQuery = true)
    List<Book> findByBook(@Param("book_name") String bookName);

    @Query(value = "select * from book order by qty desc",nativeQuery = true)
    List<Book> findAllOrderByQuantityAsc();

    @Query(value = "select * from book order by price desc",nativeQuery = true)
    List<Book> findAllOrderByPriceDesc();
    @Query(value = "select * from book order by price",nativeQuery = true)
    List<Book> findAllOrderByPriceAsc();

//    @Query(value = "update book set qty=:qty where book_name=:book_name",nativeQuery = true)
//    List<Book> editBookQty(@Param("book_name") String bookName);

}
