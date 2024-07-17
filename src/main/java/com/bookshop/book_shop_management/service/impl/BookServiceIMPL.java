package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestSaveBookDTO;
import com.bookshop.book_shop_management.entity.Author;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.reporsitory.BookREPO;
import com.bookshop.book_shop_management.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
       for(RequestSaveBookDTO r: requestSaveBookDTOok){
           Book book = new Book(
                   r.getIsbnId(),
                   r.getCategory(),
                   r.getBookTitle(),
                   author.get().getFirstName(),
                   author.get()

               );
           books.add(book);

       }
        bookRepo.saveAll(books);



       /* for (int i = 0; i < books.size(); i++) {
            Book book = new Book(
                    books.getIsbnId(),
                    books.get(i).getCategory(),
                    books.get(i).getBookName(),
                    books.get(i).getAuthorName(),
                    books.get(i).getAuthor()
            );
            bookSave.add(book);
        }
        bookRepo.saveAll(bookSave);
       */
        return "";
    }
}
