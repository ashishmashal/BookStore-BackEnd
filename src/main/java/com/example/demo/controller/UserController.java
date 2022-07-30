package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import com.example.demo.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    IUserService iUserService;

    @Autowired
    TokenUtil tokenUtil;

    List<User> userList= new ArrayList<>();

    @RequestMapping(value = {"","/", "/get"})
    public ResponseEntity<ResponseDTO> getUserData() {
        userList = iUserService.getUserData();
        ResponseDTO respOTO = new ResponseDTO("Get Call Successful", userList);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }

    @GetMapping("/getUser/{token}")
    public ResponseEntity<ResponseDTO> getUserDataById(@PathVariable String token) {
        User userData= null;
        userData = iUserService.getUserDataById(token);
        ResponseDTO respDTO= new ResponseDTO("Get Call For ID Successful", userData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<ResponseDTO> createUserData(
            @Valid @RequestBody UserDTO userDTO) {
        User userData = iUserService.createUserData(userDTO);
        ResponseDTO respOTO= new ResponseDTO("Created User  Data Successfully", userData);
        return new ResponseEntity<ResponseDTO>(respOTO, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{token}")
    public ResponseEntity<ResponseDTO> updateUserData(@PathVariable String token,@Valid @RequestBody UserDTO userDTO) {
        ResponseDTO respDTO= new ResponseDTO("Updated User Details Successfully", iUserService.updateUserData(token,userDTO));
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{token}")
    public ResponseEntity <ResponseDTO> deleteUserData(@PathVariable String token) {
        iUserService.deleteUserData(token);
        ResponseDTO respDTO= new ResponseDTO("Deleted Successfully", "Deleted id: "+token);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/loginCheck")
    public ResponseEntity<ResponseDTO> getEmailAndPass(@RequestBody LoginDTO loginDTO)
    {
        ResponseDTO respDTO= new ResponseDTO("Get Call For User Successful", iUserService.Search(loginDTO));
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/forgotPass")
    public ResponseEntity<ResponseDTO> forgotPass(@RequestParam String email,@RequestBody UserDTO userDTO){
        ResponseDTO responseDTO=new ResponseDTO("Your Password is ",iUserService.resetPassword(email,userDTO));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<ResponseDTO> verifyUser(@PathVariable String token){
        ResponseDTO responseDTO = new ResponseDTO("User verified successfully", iUserService.verifyUser(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
