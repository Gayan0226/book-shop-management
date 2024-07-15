package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.service.AuthorService;
import com.bookshop.book_shop_management.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/author-controller")
@CrossOrigin
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping(path = {"/add-author"})
    public ResponseEntity<StandardResponse> saveAuthor(@RequestBody SaveAuthorDTO saveAuthorDTO) {
        String authorName =authorService.saveAuthorDetails(saveAuthorDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Add successfull",
                        authorName
                ), HttpStatus.CREATED
        );
    }



}
