package com.example.demo.service;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Email;
import org.springframework.http.ResponseEntity;

public interface IEmailService {
    ResponseEntity<ResponseDTO> sendMail(Email email);

    String getLink(String token);
}
