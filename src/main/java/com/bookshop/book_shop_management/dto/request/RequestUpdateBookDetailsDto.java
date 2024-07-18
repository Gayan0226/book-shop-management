package com.bookshop.book_shop_management.dto.request;

import com.bookshop.book_shop_management.entity.Author;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RequestUpdateBookDetailsDto {

    String isbnId;

    String category;

    String bookName;

    String authorName;
    
    private Author author;
}
