package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.config.AdminPrinciple;
import com.bookshop.book_shop_management.entity.Admin;
import com.bookshop.book_shop_management.exceptions.NotFoundException;
import com.bookshop.book_shop_management.reporsitory.AdminRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(AdminDetailsService.class);
    private final AdminRepo repository;

    public AdminDetailsService(AdminRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = repository.findAdminByUserName(username);
       log.info("admin Password:{}", admin.getPassword());
          if (admin == null) {
            throw new NotFoundException("credential Invalid");
        }
        return new AdminPrinciple(admin);
    }
}
