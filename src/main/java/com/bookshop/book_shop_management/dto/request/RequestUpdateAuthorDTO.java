package com.bookshop.book_shop_management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateAuthorDTO {
    int authorId;
    String firstName;
    String lastName;
    String email;
    ArrayList contact;
}
