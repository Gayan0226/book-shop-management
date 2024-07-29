package com.bookshop.book_shop_management.dto.responce;

import com.bookshop.book_shop_management.entity.enums.BookCategoryType;

public interface ResponseBookSearchByAuthorEmail {
    String getIsbnId();
    BookCategoryType getCategory();
    String getBookTitle();
}
