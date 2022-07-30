package com.example.demo.model;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_registration")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String firstName;
    private  String lastname;
    private String email;
    private String password;
    private String address;
    private  String username;
    private String token;
    private boolean verify = false;

    public User(UserDTO userDTO){
        this.firstName=userDTO.firstName;
        this.lastname=userDTO.lastName;
        this.email=userDTO.email;
        this.password=userDTO.password;
        this.address=userDTO.address;
        this.username=userDTO.username;
    }

    public User(int id,UserDTO userDTO){
        this.id=id;
        this.firstName=userDTO.firstName;
        this.lastname=userDTO.lastName;
        this.email=userDTO.email;
        this.password=userDTO.password;
        this.address=userDTO.address;
        this.username=userDTO.username;
    }


}
