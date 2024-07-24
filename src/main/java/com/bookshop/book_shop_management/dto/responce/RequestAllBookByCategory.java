package com.bookshop.book_shop_management.dto.responce;

import com.bookshop.book_shop_management.entity.enums.BookCateGoryType;

public interface RequestAllBookByCategory {
    String getBookTitle();
    String getIsbnId();
    String getAuthorName();
    BookCateGoryType getCategory();
}
