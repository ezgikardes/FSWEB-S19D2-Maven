package com.workintech.s18d4.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AccountException extends RuntimeException{
    private final HttpStatus status;

    public AccountException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}