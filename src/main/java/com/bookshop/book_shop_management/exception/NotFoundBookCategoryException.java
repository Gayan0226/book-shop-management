package com.bookshop.book_shop_management.exception;

public class NotFoundBookCategoryException extends RuntimeException {
    public NotFoundBookCategoryException(String message) {
        super(message);
    }
}
