package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateBookDetailsDto;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.exception.AuthorValidationException;
import com.bookshop.book_shop_management.service.BookService;
import com.bookshop.book_shop_management.util.StandardResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/book-controller")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorValidationException authorValidationException;

    @PostMapping(
            path = {"/save-book"},
            params = {"authorId"}
    )
    public ResponseEntity<StandardResponse> saveBookDetails(
            @Valid @RequestBody List<RequestSaveBookDTO> requestSaveBookDTOok,
            @RequestParam(value = "authorId") int authorId
    ) {
        String saved = bookService.saveBookDetails(authorId, requestSaveBookDTOok);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "saved Books of author",
                        saved
                ), HttpStatus.OK
        );
    }

    @PutMapping(
            path = {"/update-book-details"},
            params = {"bookId"}
    )
    public ResponseEntity<StandardResponse> updateBookDetails(
            @RequestParam(value = "bookId") String bookId,
            @RequestBody @Valid RequestUpdateBookDetailsDto requestUpdateBook
    ) {
        String updated = bookService.updateBookByBookId(bookId, requestUpdateBook);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "saved Books of author",
                        "saved"
                ), HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"books-name-page-by-category"},
            params = {"category"}
    )
    public List<String> getBooksByAuthorName(
            @RequestParam(value = "category") String category,
            @RequestParam(value = "page") int page
    ) {
        Page<Book> booksName = bookService.getBooksByAuthorName(category, page);
        return booksName.map(Book::getBookTitle).getContent();
    }

    @DeleteMapping(
            path = {"/delete-book-by-id"},
            params = {"id"}
    )
    public ResponseEntity<StandardResponse> deleteBookById(
            @RequestParam(value = "id") String id
    ) {
        String delete = bookService.deleteBookById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "delete Books of author",
                        delete
                ), HttpStatus.OK
        );
    }

}
