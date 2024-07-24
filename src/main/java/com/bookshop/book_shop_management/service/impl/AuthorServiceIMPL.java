package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestAuthorNameContactsUpdateDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateAuthorDTO;
import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.exceptions.DuplicateValueException;
import com.bookshop.book_shop_management.exceptions.NotFoundException;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.service.AuthorService;
import com.bookshop.book_shop_management.util.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceIMPL implements AuthorService {
    private final AuthorMapper authorMapper;
    private final AuthorREPO authorREPO;
    private final Logger log = LoggerFactory.getLogger(AuthorServiceIMPL.class);

    @Override
    public String saveAuthorDetails(SaveAuthorDTO saveAuthorDTO) {
        Author author = authorMapper.dtoToEntity(saveAuthorDTO);
        List<Author> allAuthor = authorREPO.findAll();
        boolean emailHave = false;
        for (Author a : allAuthor) {
            emailHave = author.getEmail().equals(a.getEmail());
            System.out.println(emailHave);
        }
        if (!emailHave) {
            log.info("Come ");
            return authorREPO.save(author).getFirstName();
        } else {
            throw new DuplicateValueException("Duplicated Author !");
        }
    }

    @Override
    public String updateNameContactEmailAuthorById(int id, RequestAuthorNameContactsUpdateDTO authorUpdateDTO) {
        if (authorREPO.existsById(id)) {
            authorREPO.updateNameContactsById(authorUpdateDTO.getFirstName(), authorUpdateDTO.getEmail(), id);
            return authorUpdateDTO.getFirstName();
        }
        throw new NotFoundException("Not Found Author for ");
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
            throw new NotFoundException("Author not found for " + updateAuthorDTO.getAuthorId());
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

    @Override
    public Author getAuthorById(int id) {
        Optional<Author> author = authorREPO.findById(id);
        if (author.isPresent()) {
            return authorREPO.findById(id).get();
        } else {
            throw new NotFoundException("Not Found Author for " + id);
        }
    }

    @Override
    public List<Author> getAllAuthors(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Author> authors = authorREPO.findAll(pageable);
        if (authors.getTotalElements() > 0 && page <= authors.getTotalPages()) {
            return authors.getContent();
        } else if (authors.getTotalPages() <= page) {
            throw new NotFoundException("Page number are is Not valid for Now");
        } else {
            throw new NotFoundException("There Is no Authors ");
        }
    }
}
