package com.bookshop.book_shop_management.exception;

public class PageIsOverException extends RuntimeException{
    public PageIsOverException(String message){
        super(message);
    }
}
