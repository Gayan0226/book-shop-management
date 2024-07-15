package com.bookshop.book_shop_management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAuthorNameContactsUpdateDTO {
    String firstName;
    String email;
    ArrayList contact;
}
