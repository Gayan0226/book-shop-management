package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateBookDetailsDto;
import com.bookshop.book_shop_management.dto.responce.RequestAllBookByCategory;
import com.bookshop.book_shop_management.dto.responce.ResponseBookSearchByAuthorEmail;
import com.bookshop.book_shop_management.entity.Book;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface BookService {
    String saveBookDetails(int authorId, RequestSaveBookDTO requestSaveBookDTOok);

    String updateBookByBookId(String bookId, RequestUpdateBookDetailsDto requestUpdateBook);

    String deleteBookById(String id);

    Book getBookById(String id);

    Page<RequestAllBookByCategory> getAllBooks(int page);

    Page<Book> getBookBySearching(String isbn, int page);

    Page<ResponseBookSearchByAuthorEmail> getBooksByAuthorEmail(String email, int page);

    ByteArrayOutputStream generateReportPdf() throws JRException;

    HttpServletResponse generateExcel(HttpServletResponse response) throws IOException;
}
