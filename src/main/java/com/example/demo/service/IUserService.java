package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;

import java.util.List;

public interface IUserService {
    List<User> getUserData();

    User getUserDataById(String token);
    User createUserData(UserDTO userDTO);

    User verifyUser(String token);

    User updateUserData(String token, UserDTO userDTO);

    String deleteUserData(String token);

    String Search(LoginDTO loginDTO);

    String resetPassword(String email, UserDTO userDTO);
}
