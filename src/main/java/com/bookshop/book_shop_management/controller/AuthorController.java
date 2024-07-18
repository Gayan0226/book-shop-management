package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.RequestAuthorNameContactsUpdateDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateAuthorDTO;
import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.service.AuthorService;
import com.bookshop.book_shop_management.util.StandardResponse;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("api/v1/author-controller")
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping(path = {"/add-author"})
    public ResponseEntity<StandardResponse> saveAuthor(@RequestBody @Valid SaveAuthorDTO saveAuthorDTO) {
        String authorName = authorService.saveAuthorDetails(saveAuthorDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Add successfull", authorName), HttpStatus.CREATED);
    }

    @PutMapping(path = {"/author-update-by-id"})
    public ResponseEntity<StandardResponse> updateAuthor(@RequestBody RequestUpdateAuthorDTO updateAuthorDTO) {
        String update = authorService.updateAuthor(updateAuthorDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Updated", update), HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = {"/author-name-contacts-update"}, params = {"id"})
    public ResponseEntity<StandardResponse> updateAuthorById(@RequestParam(value = "id") int id, @RequestBody RequestAuthorNameContactsUpdateDTO authorUpdateDTO) {
        String update = authorService.updateNameContactEmailAuthorById(id, authorUpdateDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Updated", update), HttpStatus.OK);
    }

    @DeleteMapping(path = {"/delete-author-by-id"}, params = {"id"})
    public ResponseEntity<StandardResponse> deleteAuthorById(@RequestParam(value = "id") int id) {
        boolean deleted = authorService.authorDeletedByID(id);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Updated", deleted), HttpStatus.OK);
    }

    @GetMapping(path = {"/get-author-by-id"}, params = {"id"})
    public ResponseEntity<StandardResponse> getAuthorById(@RequestParam(value = "id") int id) {
        Author author = authorService.getAuthorById(id);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Author here", author), HttpStatus.OK);
    }

    @GetMapping(path = {"/get-authors"}, params = {"page"})
    public List<String> getAllAuthors(@RequestParam(value = "page") int page) {
        List<Author> authors = authorService.getAllAuthors(page);
        return authors.stream().map(Author::getFirstName).collect(Collectors.toList());
    }

}
