package com.bookshop.book_shop_management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUserToReactBookDTO {
    private int user;
    private String bookReact;

}
