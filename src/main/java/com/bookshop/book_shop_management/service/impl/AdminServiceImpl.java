package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.AdminDto;
import com.bookshop.book_shop_management.entity.Admin;
import com.bookshop.book_shop_management.exceptions.NotFoundException;
import com.bookshop.book_shop_management.reporsitory.AdminRepo;
import com.bookshop.book_shop_management.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final AdminRepo repository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public AdminServiceImpl(AuthenticationManager authenticationManager, AdminRepo repository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.jwtService = jwtService;

    }

    @Override
    public Admin saveAdmin(AdminDto adminDto) {
        String encodePassword = encoder.encode(adminDto.getPassword());
        return repository.save(new Admin(adminDto.getUserName(), encodePassword));
    }

    @Override
    public String generateToken(AdminDto adminDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminDto.getUserName(), adminDto.getPassword())
        );
        if (authentication.isAuthenticated()) {
            log.info("Generate Token By : {}", adminDto.getUserName());
            return jwtService.generateToken(adminDto.getUserName());
        } else {
            throw new NotFoundException("invalid credential !");
        }

    }
}
