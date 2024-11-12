package com.workintech.s18d4.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerException customerException){
        CustomerErrorResponse errorResponse = new CustomerErrorResponse(customerException.getMessage());
        return new ResponseEntity<>(errorResponse, customerException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<AddressErrorResponse> handleException(AddressException addressException){
        AddressErrorResponse errorResponse = new AddressErrorResponse(addressException.getMessage());
        return new ResponseEntity<>(errorResponse, addressException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleException(AccountException accountException){
        AccountErrorResponse errorResponse = new AccountErrorResponse(accountException.getMessage());
        return new ResponseEntity<>(errorResponse, accountException.getStatus());
    }

}