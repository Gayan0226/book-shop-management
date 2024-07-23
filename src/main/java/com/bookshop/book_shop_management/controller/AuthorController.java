package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.RequestAuthorNameContactsUpdateDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateAuthorDTO;
import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.service.AuthorService;
import com.bookshop.book_shop_management.util.StandardResponse;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
// TODO: REMOVE CONTROLLER
@RequestMapping("api/v1/author")
@CrossOrigin
public class AuthorController {
    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private AuthorService authorService;

    @PostMapping()
    public ResponseEntity<StandardResponse> saveAuthor(@RequestBody @Valid SaveAuthorDTO saveAuthorDTO) {
        String authorName = authorService.saveAuthorDetails(saveAuthorDTO);
        log.info("Author added: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Add successful", authorName), HttpStatus.OK);
    }

    @PutMapping(path = {"/author-update-by-id"})
    public ResponseEntity<StandardResponse> updateAuthor(@RequestBody RequestUpdateAuthorDTO updateAuthorDTO) {
        String update = authorService.updateAuthor(updateAuthorDTO);
        log.info("Author updated by Id: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Updated", update), HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = {"/author-name-contacts-update"}, params = {"id"})
    public ResponseEntity<StandardResponse> updateAuthorById(@RequestParam(value = "id") int id, @RequestBody RequestAuthorNameContactsUpdateDTO authorUpdateDTO) {
        String update = authorService.updateNameContactEmailAuthorById(id, authorUpdateDTO);
        log.info("Author contact name Updated by Id: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Updated", update), HttpStatus.OK);
    }

    @DeleteMapping(path = {"/delete-author-by-id"}, params = {"id"})
    public ResponseEntity<StandardResponse> deleteAuthorById(@RequestParam(value = "id") int id) {
        boolean deleted = authorService.authorDeletedByID(id);
        log.info("Author Deleted by Id: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Updated", deleted), HttpStatus.OK);
    }

    @GetMapping(path = {"/get-author-by-id"}, params = {"id"})
    public ResponseEntity<StandardResponse> getAuthorById(@RequestParam(value = "id") int id) {
        Author author = authorService.getAuthorById(id);
        log.info("Author get by Id: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Author here", author), HttpStatus.OK);
    }

    @GetMapping(path = {"/get-authors"}, params = {"page"})
    public List<String> getAllAuthors(@RequestParam(value = "page") int page) {
        List<Author> authors = authorService.getAllAuthors(page);
        log.info("Author List : ");
        return authors.stream().map(Author::getFirstName).collect(Collectors.toList());
    }

}
