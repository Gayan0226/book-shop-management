package com.bookshop.book_shop_management.dto.responce;

import java.util.ArrayList;

public interface ResponseToEmail {
    long getReactCountToEmail();
    String getEmailAuthor();
    String getBookName();
    String getBookID();
    int getAuthorID();

}
