package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestAuthorNameContactsUpdateDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateAuthorDTO;
import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.exception.DuplicateValueAddException;
import com.bookshop.book_shop_management.exception.NotFoundException;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.service.AuthorService;
import com.bookshop.book_shop_management.util.mapper.AuthorMapper;
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
            return authorREPO.save(author).getFirstName();
        } else {
            throw new DuplicateValueAddException("Duplicate Add");
        }

    }


    @Override
    public String updateNameContactEmailAuthorById(int id, RequestAuthorNameContactsUpdateDTO authorUpdateDTO) {
        if (authorREPO.existsById(id)) {
            authorREPO.updateNameContactsById(
                    authorUpdateDTO.getFirstName(),
                    authorUpdateDTO.getEmail(),
                    id);
            return authorUpdateDTO.getFirstName();
        }
        throw new NotFoundException("Not Found Author for " + id);
    }

    @Override
    public String updateAuthor(RequestUpdateAuthorDTO updateAuthorDTO) {
        if (authorREPO.existsById(updateAuthorDTO.getId())) {
            Author referenceById = authorREPO.getReferenceById(updateAuthorDTO.getId());
            referenceById.setFirstName(updateAuthorDTO.getFirstName());
            referenceById.setContact(updateAuthorDTO.getContact());
            referenceById.setEmail(updateAuthorDTO.getEmail());
            return authorREPO.save(referenceById).getFirstName();

        } else {
            throw new NotFoundException("Not Found Author for " + updateAuthorDTO.getId());
        }
    }

    @Override
    public boolean authorDeletedByID(int id) {
        if (authorREPO.existsById(id)) {
            authorREPO.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("Not Found Author for " + id);
        }
    }
}
