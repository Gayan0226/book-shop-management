package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.RequestAuthorNameContactsUpdateDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateAuthorDTO;
import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;

public interface AuthorService {


    String saveAuthorDetails(SaveAuthorDTO saveAuthorDTO);

    String updateNameContactEmailAuthorById(int id, RequestAuthorNameContactsUpdateDTO authorUpdateDTO);

    String updateAuthor(RequestUpdateAuthorDTO updateAuthorDTO);

    boolean authorDeletedByID(int id);
}
