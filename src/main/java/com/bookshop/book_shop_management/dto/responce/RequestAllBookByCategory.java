package com.bookshop.book_shop_management.dto.responce;

import com.bookshop.book_shop_management.entity.enums.BookCategoryType;

public interface RequestAllBookByCategory {
    String getBookTitle();
    String getIsbnId();
    String getAuthorName();
    BookCategoryType getCategory();
}
