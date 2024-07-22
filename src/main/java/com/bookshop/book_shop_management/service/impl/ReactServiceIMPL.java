package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestUserToReactBookDTO;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.entity.React;
import com.bookshop.book_shop_management.entity.User;
import com.bookshop.book_shop_management.exception.AuthorNotFoundException;
import com.bookshop.book_shop_management.exception.InvalidReactException;
import com.bookshop.book_shop_management.exception.NotFoundBookException;
import com.bookshop.book_shop_management.reporsitory.BookREPO;
import com.bookshop.book_shop_management.reporsitory.ReactRepo;
import com.bookshop.book_shop_management.reporsitory.UserRepo;
import com.bookshop.book_shop_management.service.ReactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReactServiceIMPL implements ReactService {
    @Autowired
    private ReactRepo reactRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookREPO bookRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String setReactBook(List<RequestUserToReactBookDTO> reacts, int userId) {
        Optional<User> user = userRepo.findById(userId);
        List<React> reactsAll = new ArrayList<>();
        if (user.isPresent()) {
            for (RequestUserToReactBookDTO r : reacts) {
                Book book = bookRepo.getReferenceById(r.getBookReact());
                String bookIdAvailable = book.getIsbnId();
                if (r.getBookReact() == bookIdAvailable) {
                    React newReact = new React(user.get(), book, r.isReact());
                    reactsAll.add(newReact);
                } else {
                    throw new AuthorNotFoundException("Books Id Invalid !");
                }
            }
            reactRepo.saveAll(reactsAll);
            return "React Successfully Added !";
        } else {
            throw new AuthorNotFoundException("There  not found Users this ID ");
        }
    }

    @Override
    public String setReactOneBook(RequestUserToReactBookDTO reacts, int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            Book book = bookRepo.getReferenceById(reacts.getBookReact());
            if (book != null) {
                React react = new React(user.get(), book, reacts.isReact());
                reactRepo.save(react);
                return "React Successfully Added !";
            } else {
                throw new AuthorNotFoundException("Books Id Invalid !");
            }

        } else {
            throw new AuthorNotFoundException("There  not found Users this ID ");
        }

    }

    @Override
    public int getReactLikeCount(String isbn) {
        Optional<Book> book = bookRepo.findById(isbn);
        if (book.isPresent()) {
            return reactRepo.getLikeCount(isbn);
        } else {
            throw new NotFoundBookException("There  not found Books this ID ");
        }

    }

    @Override
    public int getReactDisLikeCount(String isbn) {
        Optional<Book> book = bookRepo.findById(isbn);
        if (book.isPresent()) {
            return reactRepo.getDisLikeCount(isbn);
        } else {
            throw new NotFoundBookException("There  not found Books this ID ");
        }
    }
    @Override
    public int updateReact(boolean react, String isbn, int reactId) {
        Optional<React> hasReact = reactRepo.findById(reactId);
        if (hasReact.isPresent() && bookRepo.findById(isbn).isPresent()) {
            return reactRepo.updateValue(react, isbn, reactId);
        } else {
            throw new InvalidReactException("there is Not Previous Reaction");
        }

    }
}
