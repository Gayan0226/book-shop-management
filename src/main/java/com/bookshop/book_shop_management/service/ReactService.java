package com.bookshop.book_shop_management.service;

import com.bookshop.book_shop_management.dto.request.RequestUserToReactBookDTO;
import com.bookshop.book_shop_management.dto.responce.ResponseOrderBookByReact;
import com.bookshop.book_shop_management.dto.responce.ResponseToEmail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReactService {

    String setReactOneBook(RequestUserToReactBookDTO reacts, int userId);

    int getReactLikeCount(String isbn);

    int getReactDisLikeCount(String isbn);

    int updateReact(boolean react, String isbn,int reactId);

    Page<ResponseOrderBookByReact> getOrderBookByReact(int page, int size);

    List<ResponseToEmail> getEmailForSendMail();

}
