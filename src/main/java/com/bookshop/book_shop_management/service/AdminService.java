package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.AdminDto;
import com.bookshop.book_shop_management.entity.Admin;

public interface AdminService {
    Admin saveAdmin(AdminDto adminDto);

    String generateToken(AdminDto adminDto);
}
