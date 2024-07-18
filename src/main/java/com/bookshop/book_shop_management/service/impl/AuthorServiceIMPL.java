package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestAuthorNameContactsUpdateDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateAuthorDTO;
import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.exception.*;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.service.AuthorService;
import com.bookshop.book_shop_management.util.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceIMPL implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private AuthorREPO authorREPO;

    @Override
    public String saveAuthorDetails(SaveAuthorDTO saveAuthorDTO) {
        Author author = authorMapper.dtoToEntity(saveAuthorDTO);
        if (!authorREPO.existsById(author.getAuthorId())) {
            return authorREPO.save(author).getFirstName();
        } else {
            throw new DuplicateValueAddException("Duplicate Add");
        }

    }

    @Override
    public String updateNameContactEmailAuthorById(int id, RequestAuthorNameContactsUpdateDTO authorUpdateDTO) {
        if (authorREPO.existsById(id)) {
            authorREPO.updateNameContactsById(authorUpdateDTO.getFirstName(), authorUpdateDTO.getEmail(), id);
            return authorUpdateDTO.getFirstName();
        }
        throw new AuthorNotFoundException("Not Found Author for " + id);
    }

    @Override
    public String updateAuthor(RequestUpdateAuthorDTO updateAuthorDTO) {
        if (authorREPO.existsById(updateAuthorDTO.getAuthorId())) {
            Author referenceById = authorREPO.getReferenceById(updateAuthorDTO.getAuthorId());
            referenceById.setFirstName(updateAuthorDTO.getFirstName());
            referenceById.setLastName(updateAuthorDTO.getLastName());
            referenceById.setContact(updateAuthorDTO.getContact());
            referenceById.setEmail(updateAuthorDTO.getEmail());
            return authorREPO.save(referenceById).getFirstName();
        } else {
            throw new AuthorNotFoundException("Author not found for " + updateAuthorDTO.getAuthorId());
        }
    }

    @Override
    public boolean authorDeletedByID(int id) {
        if (authorREPO.existsById(id)) {
            authorREPO.deleteById(id);
            return true;
        } else {
            throw new AuthorNotFoundException("Not Found Author for " + id);
        }
    }

    @Override
    public Author getAuthorById(int id) {
        Optional<Author> author = authorREPO.findById(id);
        if (author.isPresent()) {
            return authorREPO.findById(id).get();
        } else {
            throw new AuthorNotFoundException("Not Found Author for " + id);
        }
    }

    @Override
    public List<Author> getAllAuthors(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Author> authors = authorREPO.findAll(pageable);
        if (authors.getTotalElements() > 0 && page<=authors.getTotalPages()) {
            return authors.getContent();
        } else if(authors.getTotalPages()<=page) {
           throw new PageIsOverException("Page number are is Not valid for Now");
        }else{
            throw new EmptyAuthorsException("There Is no Authors ");
        }
    }
}
