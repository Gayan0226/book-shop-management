package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.exception.DuplicateValueAddException;
import com.bookshop.book_shop_management.exception.NotFoundException;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.service.AuthorService;
import com.bookshop.book_shop_management.util.mapper.AuthorMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceIMPL implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private AuthorREPO authorREPO;

    @Override
    public String saveAuthorDetails(SaveAuthorDTO saveAuthorDTO) {
        Author author = authorMapper.dtoToEntity(saveAuthorDTO);
        if (!authorREPO.existsById(author.getId())) {
            return authorREPO.save(author).getFirstName() + " Save Author";
        } else {
            throw new DuplicateValueAddException("Duplicate Add");
        }

    }
}
