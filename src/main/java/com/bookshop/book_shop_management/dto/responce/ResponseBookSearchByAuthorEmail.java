package com.bookshop.book_shop_management.dto.responce;

import com.bookshop.book_shop_management.entity.enums.BookCateGoryType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public interface ResponseBookSearchByAuthorEmail {
    String getIsbnId();
    BookCateGoryType getCategory();
    String getBookTitle();
}
