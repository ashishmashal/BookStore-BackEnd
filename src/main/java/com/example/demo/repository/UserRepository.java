package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from user_registration where email=:email AND password =:password",nativeQuery = true)
    User find(String email,String password);

//    @Query(value = "update user_registration set password=:password where email=:email", nativeQuery = true)
//    User findByEmail(@PathVariable("email") String email);

    @Query(value = "SELECT * FROM user_registration where email=:email",nativeQuery = true)
    User findPass(String email);
}
