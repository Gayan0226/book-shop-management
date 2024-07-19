package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.RequestUserToReactBookDTO;

import java.util.List;

public interface ReactService {
    String setReactBook(List<RequestUserToReactBookDTO> reacts, boolean react);
}
