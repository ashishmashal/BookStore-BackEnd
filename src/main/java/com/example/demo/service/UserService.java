package com.example.demo.service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.BookException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Email;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    IEmailService iEmailService;

    @Override
    public List<User> getUserData() {
        return userRepository.findAll();
    }
    @Override
    public User getUserDataById(String token) {
        int id = tokenUtil.decodeToken(token);
        return userRepository.findById(id).orElseThrow(() -> new UserException("User Id With " +
                id + " Does Not Exist"));
    }
    @Override
    public User createUserData(UserDTO userDTO){
        User userData = new User(userDTO);
        userRepository.save(userData);
        String token = tokenUtil.createToken(userData.getId());
        userData.setToken(token);
        Email email = new Email(userData.getEmail(),"User Is Registered",userData.getFirstName() + "\n" +userData.getLastname() + "\n" +
                userData.getEmail()+"\n" + userData.getAddress() + "\n" + userData.getToken()+"\n"+userData.getPassword() + "=>" + iEmailService.getLink(token));
        iEmailService.sendMail(email);
        return userRepository.save(userData);
    }
    @Override
    public User verifyUser(String token) {
        User user = this.getUserDataById(token);
        user.setVerify(true);
        userRepository.save(user);
        return user;
    }
    @Override
    public User updateUserData(String token, UserDTO userDTO){
        int id = tokenUtil.decodeToken(token);
        if (userRepository.findById(id).isPresent()){
            User bookDetails1=new User(id,userDTO);
            userRepository.save(bookDetails1);
            return bookDetails1;
        }
        else {
            throw new UserException("Id not found ");
        }
    }
    @Override
    public String deleteUserData(String token) {
        int ID = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(ID);
        if (user.isPresent()){
            userRepository.deleteById(ID);
            return "Data Deleted";
        }
        throw new BookException("User id " + ID +" is not found");
    }
    @Override
    public String Search(LoginDTO loginDTO) {
        User userDetails=userRepository.find(loginDTO.email, loginDTO.password);
        if(userDetails == null)
        {
            throw new UserException(" Book  Store with User "+ loginDTO.email + " not found!");
        }
        return "USer Logged in Successfully";
    }
    @Override
    public String resetPassword(String email, UserDTO userDTO) {
        User registrationDetails = userRepository.findPass(email);
        if (registrationDetails != null) {
            registrationDetails.setPassword(userDTO.getPassword());
            userRepository.save(registrationDetails);
            return "Password Updated";
        } else {
            return "Wrong Email Id Provide !!!";
        }
    }
}
