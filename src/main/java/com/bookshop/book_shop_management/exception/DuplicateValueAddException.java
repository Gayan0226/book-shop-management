package com.bookshop.book_shop_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class DuplicateValueAddException extends RuntimeException {
    public DuplicateValueAddException(String message) {
        super(message);
    }
}
