package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.request.RequestUserToReactBookDTO;
import com.bookshop.book_shop_management.dto.responce.ResponseOrderBookByReact;
import com.bookshop.book_shop_management.dto.responce.ResponseToEmail;
import com.bookshop.book_shop_management.entity.Book;
import com.bookshop.book_shop_management.entity.React;
import com.bookshop.book_shop_management.entity.User;
import com.bookshop.book_shop_management.exceptions.NotFoundException;
import com.bookshop.book_shop_management.reporsitory.BookREPO;
import com.bookshop.book_shop_management.reporsitory.ReactRepo;
import com.bookshop.book_shop_management.reporsitory.UserRepo;
import com.bookshop.book_shop_management.service.ReactService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReactServiceIMPL implements ReactService {

    private static final Logger log = LoggerFactory.getLogger(ReactServiceIMPL.class);
    private final ReactRepo reactRepo;

    private final UserRepo userRepo;

    private final BookREPO bookRepo;

    @Override
    public String setReactOneBook(RequestUserToReactBookDTO reacts, int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            Book book = bookRepo.getReferenceById(reacts.getBookReact());
            if (bookRepo.existsById(book.getIsbnId())) {
                React react = new React(user.get(), book, reacts.isReact());
                reactRepo.save(react);
                log.info("React added");
                return "React Successfully Added !";
            } else {
                throw new NotFoundException("Books Id Invalid !");
            }

        } else {
            throw new NotFoundException("There  not found Users this ID ");
        }
    }

    @Override
    public int getReactLikeCount(String isbn) {
        Optional<Book> book = bookRepo.findById(isbn);
        if (book.isPresent()) {
            log.info("Book Like Count");
            return reactRepo.getLikeCount(isbn);
        } else {
            throw new NotFoundException("There  not found Books this ID ");
        }
    }

    @Override
    public int getReactDisLikeCount(String isbn) {
        Optional<Book> book = bookRepo.findById(isbn);
        if (book.isPresent()) {
            log.info("Book DisLike Count");
            return reactRepo.getDisLikeCount(isbn);
        } else {
            throw new NotFoundException("There  not found Books this ID ");
        }
    }

    @Override
    public int updateReact(boolean react, String isbn, int reactId) {
        Optional<React> hasReact = reactRepo.findById(reactId);
        Optional<Book> book = bookRepo.findById(isbn);
        if (hasReact.isPresent() && book.isPresent()) {
            React reactData = hasReact.get();
            reactData.setReact(react);
            reactData.setReactId(reactId);
            reactRepo.save(reactData);
            log.info("Book Update React");
            return reactId;
        } else {
            throw new NotFoundException("there is Not Previous Reaction");
        }
    }

    @Override
    public Page<ResponseOrderBookByReact> getOrderBookByReact(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ResponseOrderBookByReact> reactOrder = reactRepo.findAllBookByReactOrder(pageable);
        if (!reactOrder.isEmpty()) {
            log.info("Get Books By React Order");
            return reactOrder;
        } else {
            throw new NotFoundException("Not Found Any Reaction ");
        }
    }

    @Override
    public List<ResponseToEmail> getEmailForSendMail() {

        List<ResponseToEmail> reactRepoEmailToSendMail = reactRepo.getEmailToSendMail();

        if (!reactRepoEmailToSendMail.isEmpty()) {
            log.info("Get Email To Send Mail");
            return reactRepoEmailToSendMail;

        } else {
            log.info("React Table Empty!");
            throw new NotFoundException("There Is Not Valid Email");
        }
    }
}
