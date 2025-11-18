package com.bookshop.book_shop_management.exceptions;

public class JwtInvalid extends RuntimeException{
    public JwtInvalid(String message){
        super(message);
    }
}
