package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateBookDetailsDto;
import com.bookshop.book_shop_management.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    String saveBookDetails(int authorId, List<RequestSaveBookDTO> requestSaveBookDTOok);

    String updateBookByBookId(String bookId, RequestUpdateBookDetailsDto requestUpdateBook);

    Page<Book> getBooksByAuthorName(String category, int page);

    String deleteBookById(String id);
}
