package com.bookshop.book_shop_management.exception;

public class EmptyDetailsException extends RuntimeException {
    public EmptyDetailsException(String message) {
        super(message);
    }
}
