package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestUserToReactBookDTO;
import com.bookshop.book_shop_management.service.ReactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactServiceIMPL implements ReactService {
    @Override
    public String setReactBook(List<RequestUserToReactBookDTO> reacts, boolean react) {
        System.out.println("reacts: " + react);
        return "";
    }
}
