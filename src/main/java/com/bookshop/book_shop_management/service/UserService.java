package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.RequestUserSaveDTO;

public interface UserService {
    String userRegister(RequestUserSaveDTO user);
}
