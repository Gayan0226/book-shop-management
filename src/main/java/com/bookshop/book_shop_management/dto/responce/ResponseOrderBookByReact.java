package com.bookshop.book_shop_management.dto.responce;

import java.util.ArrayList;

public interface ResponseOrderBookByReact {
    String getIsbnId();
    long getReactCount();
    String getFirstName();
    String getEmail();
    ArrayList getContact();
}
