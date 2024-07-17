package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;

import java.util.List;

public interface BookService {
    String saveBookDetails(int authorId, List<RequestSaveBookDTO> requestSaveBookDTOok);
}
