package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "First Name cannot be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "First Name Invalid")
    public String firstName;

    @NotNull(message = "Last Name cannot be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Last Name Invalid")
    public  String lastName;

    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[a-z0-9]+@[a-z0-9]+.[a-z]{2,3}+.[a-z]{2,}$",message = "Email Is Invalid")
    public String email;

    @NotNull(message = "Password Cannot Be Null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&-+=()]).{8,}$",message = "Password Is Invalid")
    public String password;

    @NotNull(message = "Address Cannot Be Empty")
    @Pattern(regexp = "^[0-9\\\\\\/# ,a-zA-Z]+[ ,]+[0-9\\\\\\/#, a-zA-Z]{1,}",message = "Address Is Invalid")
    public String address;
    @NotNull(message = "Username cannot be null")
    @Pattern(regexp = "^[a-zA-Z\\s]{3,}$", message = "User Name Invalid")
    public String username;
}
//@GeneratedValue(strategy = generationtype.sequence)