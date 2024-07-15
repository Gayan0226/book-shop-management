package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/author-controller")
@CrossOrigin
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping(path = {"/add-author"})



}
