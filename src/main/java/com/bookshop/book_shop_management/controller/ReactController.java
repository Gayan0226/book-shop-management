package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.RequestUserToReactBookDTO;
import com.bookshop.book_shop_management.service.ReactService;
import com.bookshop.book_shop_management.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/react-controller")
@CrossOrigin

public class ReactController {
    @Autowired
    private ReactService reactService;

    @PostMapping(
            path = {"/react-to-all-book"},
            params = {"userId"}
    )
    public ResponseEntity<StandardResponse> reactToBook(
            @RequestParam(value = "userId", defaultValue = "like") int userId,
            @RequestBody List<RequestUserToReactBookDTO> reacts) {
        String reactBooks = reactService.setReactBook(reacts,userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "React successful", reactBooks), HttpStatus.OK);
    }
    @PostMapping(
            path = {"/react-to-book"},
            params = {"userId"}
    )
    public ResponseEntity<StandardResponse> reactToBook(
            @RequestParam(value = "userId", defaultValue = "like") int userId,
            @RequestBody RequestUserToReactBookDTO reacts) {
        String reactBooks = reactService.setReactOneBook(reacts,userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "React successful", reactBooks), HttpStatus.OK);
    }
    @GetMapping(
            path = {"/book-like-count"},
            params = {"isbn"}
    )
    public ResponseEntity<StandardResponse> bookLikeCount(@RequestParam(value ="isbn") String isbn) {
        int reactCount = reactService.getReactLikeCount(isbn);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "React successful", reactCount), HttpStatus.OK);
    }

    @GetMapping(
            path = {"/book-dislike-count"},
            params = {"isbn"}
    )
    public ResponseEntity<StandardResponse> bookDisLikeCount(@RequestParam(value ="isbn") String isbn) {
        int reactCount = reactService.getReactDisLikeCount(isbn);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "React successful", reactCount), HttpStatus.OK);
    }

}
