package com.bookshop.book_shop_management.controller;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateBookDetailsDto;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.exception.AuthorValidationException;
import com.bookshop.book_shop_management.exception.PageIsOverException;
import com.bookshop.book_shop_management.reporsitory.BookREPO;
import com.bookshop.book_shop_management.service.BookService;
import com.bookshop.book_shop_management.util.StandardResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//TODo the Book Controller And it details Changing  
@Validated
@RestController
@RequestMapping("api/v1/book-controller")
@CrossOrigin
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    private final AuthorValidationException authorValidationException;

    public BookController(BookService bookService, AuthorValidationException authorValidationException) {
        this.bookService = bookService;
        this.authorValidationException = authorValidationException;
    }

    @PostMapping(path = {"/add-book"}, params = {"authorId"})
    public ResponseEntity<StandardResponse> saveBookDetails(@Valid @RequestBody RequestSaveBookDTO requestSaveBookDTOok, @RequestParam(value = "authorId") int authorId) {
        String saved = bookService.saveBookDetails(authorId, requestSaveBookDTOok);
        log.info("Book added: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "saved Books of author", saved), HttpStatus.OK);
    }

    @PutMapping(path = {"/update-book-details"}, params = {"bookId"})
    public ResponseEntity<StandardResponse> updateBookDetails(@RequestParam(value = "bookId") String bookId, @RequestBody @Valid RequestUpdateBookDetailsDto requestUpdateBook) {
        String updated = bookService.updateBookByBookId(bookId, requestUpdateBook);
        log.info("Book Updated: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "saved Books of author", "saved"), HttpStatus.OK);
    }

    @GetMapping(path = {"books-name-page-by-category"}, params = {"category"})
    public List<String> getBooksByAuthorName(@RequestParam(value = "category") String category, @RequestParam(value = "page") int page) {
        Page<Book> booksName = bookService.getBooksByAuthorName(category, page);
        log.info("Book get Like Page By category: ");
        return booksName.map(Book::getBookTitle).getContent();
    }

    @DeleteMapping(path = {"/delete-book-by-id"}, params = {"id"})
    public ResponseEntity<StandardResponse> deleteBookById(@RequestParam(value = "id") String id) {
        String delete = bookService.deleteBookById(id);
        log.info("Book deleted: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "delete Books of author", delete), HttpStatus.OK);
    }

    @GetMapping(path = {"/book-by-id"}, params = {"id"})
    public ResponseEntity<StandardResponse> getBookById(@RequestParam(value = "id") String id) {
        Book book = bookService.getBookById(id);
        log.info("Book retrieved: ");
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "there IS books ", book), HttpStatus.OK);
    }

    @GetMapping(path = {"/all-books"}, params = {"page"})
    public Page<Book> getAllBooks(@RequestParam(value = "page") int page) {
        Page<Book> books = bookService.getAllBokks(page);
        List<String> bookList = new ArrayList<>();
        log.info("Book List Retrieved: ");
        for (Book book : books.stream().toList()) {
            bookList.add(book.getBookTitle());
        }
        return books;
    }

    @GetMapping(path = {"/isbn-search"}, params = {"isbn", "page"})
    public Page<Book> getBookByISbnSearching(@RequestParam(value = "isbn") String isbn, @RequestParam(value = "page") int page) {

        Page<Book> books = bookService.getBookBySearching(isbn, page);
        log.info("Book Search Retrieved: ");
        return books;
    }
}
