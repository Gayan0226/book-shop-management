package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateBookDetailsDto;
import com.bookshop.book_shop_management.dto.responce.RequestAllBookByCategory;
import com.bookshop.book_shop_management.dto.responce.ResponseBookSearchByAuthorEmail;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.entity.enums.BookCateGoryType;
import com.bookshop.book_shop_management.exception.*;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.reporsitory.BookREPO;
import com.bookshop.book_shop_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceIMPL implements BookService {

    private final ModelMapper modelMapper;

    private final AuthorREPO authorRepo;

    private final BookREPO bookRepo;

    @Override
    public String saveBookDetails(int authorId, RequestSaveBookDTO requestSaveBookDTOok) {
        Optional<Author> author = authorRepo.findById(authorId);
        if (author.isPresent()) {
            Book book = modelMapper.map(requestSaveBookDTOok, Book.class);
            Optional<Book> bookHave = bookRepo.findById(requestSaveBookDTOok.getIsbnId());
            if (!bookHave.isPresent()) {
                try {
                    BookCateGoryType categoryType = BookCateGoryType.valueOf(requestSaveBookDTOok.getCategory().toUpperCase());
                    book.setAuthor(author.get());
                    book.setCategory(categoryType);
                    bookRepo.save(book);
                    return author.get().getFirstName();
                } catch (IllegalArgumentException e) {
                    throw new NotFoundBookException("Category not found");
                }
            } else {
                throw new DuplicateValueAddException("Books already exist");
            }
        } else {
            throw new AuthorNotFoundException("Author not Found");
        }
    }

    @Override
    public String updateBookByBookId(String bookId, RequestUpdateBookDetailsDto requestUpdateBook) {
        Optional<Book> book = bookRepo.findById(bookId);
        if (book.isPresent()) {
            bookRepo.updateBookDetails(requestUpdateBook.getBookTitle(), requestUpdateBook.getCategory(), bookId);
            return bookId;
        }
        else{
            throw new NotFoundBookException("Book not found");
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
    public Page<RequestAllBookByCategory> getAllBooks(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<RequestAllBookByCategory> books = bookRepo.findx(pageable);
        if (books.getSize() > 0 && page < books.getTotalPages()) {
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
        Page<Book> bookSearch = bookRepo.findAllSearch(isbn, pageable);
        if (bookSearch.getTotalElements() > 0 && page < bookSearch.getTotalPages()) {
            return bookSearch;
        } else if (page > bookSearch.getTotalPages()) {
            throw new PageIsOverException("Page not available!");
        } else {
            throw new NotISBNException("Invalid ISBN");
        }
    }

    @Override
    public Page<ResponseBookSearchByAuthorEmail> getBooksByAuthorName(String email, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<ResponseBookSearchByAuthorEmail> books = bookRepo.findSearchBookByEmail(email,pageable);
        return books;
    }
}
