package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestUserSaveDTO;
import com.bookshop.book_shop_management.entity.User;
import com.bookshop.book_shop_management.reporsitory.UserRepo;
import com.bookshop.book_shop_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
private final Logger log = LoggerFactory.getLogger(UserServiceIMPL.class);
    @Override
    public String userRegister(RequestUserSaveDTO user) {
        User userDetails = modelMapper.map(user, User.class);
            userRepo.save(userDetails);
            log.info("Add user : {}", userDetails);
            return "add";
    }
}
