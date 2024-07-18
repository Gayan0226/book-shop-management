package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateBookDetailsDto;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.exception.*;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.reporsitory.BookREPO;
import com.bookshop.book_shop_management.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceIMPL implements BookService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthorREPO authorRepo;
    @Autowired
    private BookREPO bookRepo;

    @Override
    public String saveBookDetails(int authorId, List<RequestSaveBookDTO> requestSaveBookDTOok) {
        Optional<Author> author = authorRepo.findById(authorId);
        List<Book> books = new ArrayList<>();
        if (author.isPresent()) {
            for (RequestSaveBookDTO r : requestSaveBookDTOok) {
                Book book = new Book(r.getIsbnId(), r.getCategory(), r.getBookTitle(), author.get().getFirstName(), author.get());
                books.add(book);
            }
            bookRepo.saveAll(books);
            return author.get().getFirstName();
        } else {
            throw new AuthorNotFoundException("Author not Found");
        }
    }

    @Override
    public String updateBookByBookId(String bookId, RequestUpdateBookDetailsDto requestUpdateBook) {
        Optional<Book> book = bookRepo.findById(bookId);
        if (book.isPresent()) {
            bookRepo.updateBookDetails(requestUpdateBook.getBookTitle(), requestUpdateBook.getAuthorName(), requestUpdateBook.getCategory(), bookId);
            return bookId;
        }
        return null;
    }

    @Override
    public Page<Book> getBooksByAuthorName(String category, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Optional<Book> books = bookRepo.findFirstByCategoryEquals(category);
        if (books.isPresent()) {
            return bookRepo.findAllByCategoryEquals(category, pageable);
        } else {
            throw new NotFoundBookCategoryException("There is no book category searching  you");
        }
    }

    @Override
    public String deleteBookById(String id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            bookRepo.deleteById(id);
            return id;
        } else {
            throw new NotFoundBookException("There is No book found");
        }
    }

    @Override
    public Book getBookById(String id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new NotFoundBookException("There is No book found");
        }
    }

    @Override
    public Page<Book> getAllBokks(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Book> books = bookRepo.findAll(pageable);
        System.out.println(books.getTotalPages());
        if (books.getSize() > 0 && page<books.getTotalPages() ) {
            return books;
        } else if (page > books.getTotalPages()) {
            throw new PageIsOverException("There's no more pages");
        } else {
            throw new NotFoundBookException("There is no book found");
        }
    }

    @Override
    public Page<Book> getBookBySearching(String isbn, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Book> bookSearch =bookRepo.findAllSea(isbn,pageable);
        if(bookSearch.getTotalElements()>0 && page<bookSearch.getTotalPages()){
            return bookSearch;
        }
        else if(page>bookSearch.getTotalPages()){
            throw new PageIsOverException("There Is Not Available size page");
        }
        else{
            throw new NotISBNException("There Is No Books For this ISBN Searched");
        }

    }
}
