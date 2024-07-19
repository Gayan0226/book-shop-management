package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.RequestUserToReactBookDTO;
import com.bookshop.book_shop_management.exception.InvalidReactException;
import com.bookshop.book_shop_management.service.ReactService;
import com.bookshop.book_shop_management.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
            path = {"/react-to-book"},
            params = {"react"}
    )
    public ResponseEntity<StandardResponse> reactToBook(
            @RequestParam(value = "react", defaultValue = "like") String react,
            @RequestBody List<RequestUserToReactBookDTO> reacts) {

        if (react.equalsIgnoreCase("like") || react.equals("dislike")) {
            String reactBooks = reactService.setReactBook(reacts,react.equalsIgnoreCase("like")?true:false);

            return null;
        } else {
            throw new InvalidReactException("Please enter a valid react --> like or dislike react");
        }
    }
}
