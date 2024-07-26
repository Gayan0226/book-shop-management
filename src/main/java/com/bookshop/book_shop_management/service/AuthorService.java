package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.RequestUpdateAuthorDTO;
import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;

import java.util.List;

public interface AuthorService {
    String saveAuthorDetails(SaveAuthorDTO saveAuthorDTO);

    String updateAuthor(RequestUpdateAuthorDTO updateAuthorDTO);

    boolean authorDeletedByID(int id);

    Author getAuthorById(int id);

    List<Author> getAllAuthors(int page);
}
