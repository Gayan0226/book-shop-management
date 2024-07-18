package com.bookshop.book_shop_management.exception;

public class NotFoundBookException extends RuntimeException {
    public NotFoundBookException(String message) {
        super(message);
    }
}
