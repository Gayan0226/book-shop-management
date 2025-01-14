package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.AdminDto;
import com.bookshop.book_shop_management.entity.Admin;
import com.bookshop.book_shop_management.reporsitory.AdminRepo;
import com.bookshop.book_shop_management.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepo repository;
private final BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);
    public AdminServiceImpl(AdminRepo repository) {
        this.repository = repository;
    }

    @Override
    public Admin saveAdmin(AdminDto adminDto) {
        String encodePassword = encoder.encode(adminDto.getPassword());
        return repository.save(new Admin(adminDto.getUserName(), encodePassword));
    }
}
