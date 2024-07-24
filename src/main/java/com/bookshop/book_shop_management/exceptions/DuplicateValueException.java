package com.bookshop.book_shop_management.exceptions;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException(String message) {
        super(message);
    }
}
