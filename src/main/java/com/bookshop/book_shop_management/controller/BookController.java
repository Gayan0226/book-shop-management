package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateBookDetailsDto;
import com.bookshop.book_shop_management.dto.responce.RequestAllBookByCategory;
import com.bookshop.book_shop_management.dto.responce.ResponseBookSearchByAuthorEmail;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.advice.Advice;
import com.bookshop.book_shop_management.service.BookService;
import com.bookshop.book_shop_management.util.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/v1/book")
@CrossOrigin
@RequiredArgsConstructor
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    private final Advice advice;

    @PostMapping(path = {"/add-book"}, params = {"authorId"})
    public ResponseEntity<StandardResponse> saveBookDetails(@Valid @RequestBody RequestSaveBookDTO requestSaveBookDTOok, @RequestParam(value = "authorId") int authorId) {
        String saved = bookService.saveBookDetails(authorId, requestSaveBookDTOok);
        log.info("Book added: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "saved Books of author", saved), HttpStatus.OK);
    }

    @PutMapping(path = {"/book"}, params = {"bookId"})
    public ResponseEntity<StandardResponse> updateBookDetails(@RequestParam(value = "bookId") String bookId, @RequestBody @Valid RequestUpdateBookDetailsDto requestUpdateBook) {
        String updated = bookService.updateBookByBookId(bookId, requestUpdateBook);
        log.info("Book Updated: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "saved Books of author", "saved"), HttpStatus.OK);
    }

    @GetMapping(path = {"/book-author-email"}, params = {"email"})
    public Page<ResponseBookSearchByAuthorEmail> getBookByAuthorEmail(@RequestParam(value = "email") String email, @RequestParam(value = "page") int page) {
        Page<ResponseBookSearchByAuthorEmail> books = bookService.getBooksByAuthorEmail(email, page);
        log.info("Book get Like Page By Author Email: ");
        return books;
    }

    @DeleteMapping(path = {"/by-id"}, params = {"id"})
    public ResponseEntity<StandardResponse> deleteBookById(@RequestParam(value = "id") String id) {
        String delete = bookService.deleteBookById(id);
        log.info("Book deleted: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "delete Books of author", delete), HttpStatus.OK);
    }

    @GetMapping(path = {"/by-id"}, params = {"id"})
    public ResponseEntity<StandardResponse> getBookById(@RequestParam(value = "id") String id) {
        Book book = bookService.getBookById(id);
        log.info("Book retrieved: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "there IS books ", book), HttpStatus.OK);
    }

    @GetMapping(path = {"/all-books"}, params = {"page"})
    public Page<RequestAllBookByCategory> getAllBooks(@RequestParam(value = "page") int page) {
        Page<RequestAllBookByCategory> books = bookService.getAllBooks(page);
        return books;
    }

    @GetMapping(path = {"/search-isbn"}, params = {"isbn", "page"})
    public Page<Book> getBookByISbnSearching(@RequestParam(value = "isbn") String isbn, @RequestParam(value = "page") int page) {
        Page<Book> books = bookService.getBookBySearching(isbn, page);
        log.info("Book Search Retrieved: ");
        return books;
    }
}
