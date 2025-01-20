package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestReportPdf;
import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.dto.request.RequestUpdateBookDetailsDto;
import com.bookshop.book_shop_management.dto.responce.RequestAllBookByCategory;
import com.bookshop.book_shop_management.dto.responce.ResponseBookSearchByAuthorEmail;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.entity.enums.BookCategoryType;
import com.bookshop.book_shop_management.exceptions.DuplicateValueException;
import com.bookshop.book_shop_management.exceptions.NotFoundException;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.reporsitory.BookREPO;
import com.bookshop.book_shop_management.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BookServiceIMPL implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceIMPL.class);
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
                    BookCategoryType categoryType = BookCategoryType.valueOf(requestSaveBookDTOok.getCategory().toUpperCase());
                    book.setAuthor(author.get());
                    book.setCategory(categoryType);
                    bookRepo.save(book);
                    log.info("Book saved ");
                    return author.get().getFirstName();
                } catch (IllegalArgumentException e) {
                    throw new NotFoundException("Category not found");
                }
            } else {
                throw new DuplicateValueException("Books already exist");
            }
        } else {
            throw new NotFoundException("Author not Found");
        }
    }

    @Override
    public String updateBookByBookId(String bookId, RequestUpdateBookDetailsDto requestUpdateBook) {
        Optional<Book> book = bookRepo.findById(bookId);
        if (book.isPresent()) {
            try {
                BookCategoryType categoryType = BookCategoryType.valueOf(requestUpdateBook.getCategory().toUpperCase());
                Book optionalBook = book.get();
                optionalBook.setCategory(categoryType);
                optionalBook.setBookTitle(requestUpdateBook.getBookTitle());
                bookRepo.save(optionalBook);
                log.info("Book updated ");
                return bookId;
            } catch (IllegalArgumentException e) {
                throw new NotFoundException("Category not found");
            }

        } else {
            throw new NotFoundException("Book not found");
        }
    }

    @Override
    public String deleteBookById(String id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            bookRepo.deleteById(id);
            log.info("Book deleted ");
            return id;
        } else {
            throw new NotFoundException("There is No book found");
        }
    }

    @Override
    public Book getBookById(String id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            log.info("Book found ");
            return book.get();
        } else {
            throw new NotFoundException("There is No book found");
        }
    }

    @Override
    public Page<RequestAllBookByCategory> getAllBooks(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<RequestAllBookByCategory> books = bookRepo.findAllBooks(pageable);
        if (books.getSize() > 0 && page < books.getTotalPages()) {
            log.info("Books found return page ! ");
            return books;
        } else if (page > books.getTotalPages()) {
            throw new NotFoundException("There's no more pages");
        } else {
            throw new NotFoundException("There is no book found");
        }
    }

    @Override
    public Page<Book> getBookBySearching(String isbn, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Book> bookSearch = bookRepo.findAllSearch(isbn, pageable);
        if (bookSearch.getTotalElements() > 0 && page < bookSearch.getTotalPages()) {
            log.info("Books Search by ISBN return page ! ");
            return bookSearch;
        } else if (page > bookSearch.getTotalPages()) {
            throw new NotFoundException("Page not available!");
        } else {
            throw new NotFoundException("Invalid ISBN");
        }
    }

    @Override
    public Page<ResponseBookSearchByAuthorEmail> getBooksByAuthorEmail(String email, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<ResponseBookSearchByAuthorEmail> books = bookRepo.findSearchBookByAuthorEmail(email, pageable);
        if (!books.isEmpty()) {
            log.info("Books found by Author Email return page ! ");
            return books;
        } else {
            throw new NotFoundException("There is no book found for email");
        }
    }

    @Override
    public ByteArrayOutputStream generateReportPdf() throws JRException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        List<RequestReportPdf> bookDetailList = new ArrayList<>(bookRepo.findAll().stream().map(book -> new RequestReportPdf(
                book.getIsbnId(),
                book.getCategory().toString(),
                book.getBookTitle()
        )).toList());
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/report/bookDetailsReport.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(resourceAsStream);
        JRBeanCollectionDataSource bookReportBean = new JRBeanCollectionDataSource(bookDetailList);
        Map<String, Object> params = new HashMap<>();
        params.put("bookReportNewDetails", new JRBeanCollectionDataSource(bookDetailList));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, bookReportBean);
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCompressed(true);
        configuration.setMetadataAuthor("Gayan");
        String filePath = "/apps/pdf_files/" + jasperPrint.getName() + "_" + LocalDate.now() + ".pdf";
        byte[] currentReport = JasperExportManager.exportReportToPdf(jasperPrint);
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), currentReport);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Pdf Report saved to file system at location: {}", filePath);
        exporter.setConfiguration(configuration);

        exporter.exportReport();

        return byteArrayOutputStream;
    }

    @Override
    public byte[] generateExcel(HttpServletResponse response) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<Book> books = bookRepo.findAll();
        XSSFWorkbook workBook = new XSSFWorkbook();
        String reportName = "BookShop" + LocalDate.now();
        Sheet sheet = workBook.createSheet("Book_Details");
        String filePath = "/app/excel_files/" + reportName + ".xlsx";
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("AuthorID");
        row.createCell(1).setCellValue("Book Title");
        row.createCell(2).setCellValue("Create Date");
        int rowIndex = 1;
        for (Book book : books) {
            Row rowNumber = sheet.createRow(rowIndex);
            rowNumber.createCell(0).setCellValue(book.getAuthor().getAuthorId());
            rowNumber.createCell(1).setCellValue(book.getBookTitle());
            rowNumber.createCell(2).setCellValue(book.getCreateTime());
            rowIndex++;
        }
// Write the data to the HTTP response output stream.
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=book_report.xlsx");
        workBook.write(outputStream);
        workBook.close();
        outputStream.close();
        FileUtils.writeByteArrayToFile(new File(filePath), outputStream.toByteArray());
        log.info("Excel Report saved to file system at location: {}", filePath);

        return outputStream.toByteArray();
    }


}
