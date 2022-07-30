package com.example.demo.exception;

import com.example.demo.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errMsg = errorList.stream()
                .map(objErr -> objErr.getDefaultMessage())
                .collect(Collectors.toList());
        ResponseDTO respDTO = new ResponseDTO("Exception while processing rest request",errMsg);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseDTO> handleUserException(UserException exception){
        ResponseDTO respDTO = new ResponseDTO("Exception while processing REST Request",
                exception.getMessage());
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ResponseDTO> handleCartException(CartException cartException){
        ResponseDTO respDTO = new ResponseDTO("Exception while processing REST Request",
                cartException.getMessage());
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseDTO> handleEmployeePayrollException(BookException exception){
        ResponseDTO respDTO = new ResponseDTO("Exception while processing REST request",
                exception.getMessage());
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ResponseDTO> handleUserException(OrderException exception){
        ResponseDTO respDTO = new ResponseDTO("Exception while processing REST Request",
                exception.getMessage());
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.BAD_REQUEST);
    }
}
