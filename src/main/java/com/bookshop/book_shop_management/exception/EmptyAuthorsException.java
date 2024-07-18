package com.bookshop.book_shop_management.exception;

public class EmptyAuthorsException extends RuntimeException {
    public EmptyAuthorsException(String message) {
        super(message);
    }
}
