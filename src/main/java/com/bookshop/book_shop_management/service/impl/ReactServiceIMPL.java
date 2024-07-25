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
    public String setReactBook(List<RequestUserToReactBookDTO> reacts, int userId) {
        Optional<User> user = userRepo.findById(userId);
        List<React> reactsAll = new ArrayList<>();
        if (user.isPresent()) {
            for (RequestUserToReactBookDTO r : reacts) {
                Book book = bookRepo.getReferenceById(r.getBookReact());
                String bookIdAvailable = book.getIsbnId();
                if (Objects.equals(r.getBookReact(), bookIdAvailable)) {
                    React newReact = new React(user.get(), book, r.isReact());
                    reactsAll.add(newReact);
                } else {
                    throw new NotFoundException("Books Id Invalid !");
                }
            }
            reactRepo.saveAll(reactsAll);
            return "React Successfully Added !";
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public String setReactOneBook(RequestUserToReactBookDTO reacts, int userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent()) {
            Book book = bookRepo.getReferenceById(reacts.getBookReact());
            if (bookRepo.existsById(book.getIsbnId())) {
                React react = new React(user.get(), book, reacts.isReact());
                reactRepo.save(react);
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
            return reactRepo.getLikeCount(isbn);
        } else {
            throw new NotFoundException("There  not found Books this ID ");
        }
    }

    @Override
    public int getReactDisLikeCount(String isbn) {
        Optional<Book> book = bookRepo.findById(isbn);
        if (book.isPresent()) {
            return reactRepo.getDisLikeCount(isbn);
        } else {
            throw new NotFoundException("There  not found Books this ID ");
        }
    }

    @Override
    public int updateReact(boolean react, String isbn, int reactId) {
        Optional<React> hasReact = reactRepo.findById(reactId);
        if (hasReact.isPresent() && bookRepo.findById(isbn).isPresent()) {
            return reactRepo.updateValue(react, isbn, reactId);
        } else {
            throw new NotFoundException("there is Not Previous Reaction");
        }
    }

    @Override
    public Page<ResponseOrderBookByReact> getOrderBookByReact(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ResponseOrderBookByReact> reactOrder = reactRepo.findAllBookByReactOrder(pageable);
        if(!reactOrder.isEmpty()){
            return reactOrder;
        }
        else{
            throw new NotFoundException("Not Found Any Reaction ");
        }
    }

    @Override
    public List<ResponseToEmail> getEmailForSendMail() {

        List<ResponseToEmail> reactRepoEmailToSendMail = reactRepo.getEmailToSendMail();

        if (!reactRepoEmailToSendMail.isEmpty()) {
//            Set<String> emailSet = new HashSet<>();
//            List<ResponseToEmail> uniqueEmails = new ArrayList<>();
//
//            for (ResponseToEmail response : reactRepoEmailToSendMail) {
//                if (emailSet.add(response.getEmailAuthor())) {
//                    uniqueEmails.add(response);
//                }
//            }

            return reactRepoEmailToSendMail;

        } else {
            log.info("React Table Empty!");
            throw new NotFoundException("There Is Not Valid Email");
        }
    }
}
