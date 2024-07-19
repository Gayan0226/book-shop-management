package com.bookshop.book_shop_management.dto.request;

import com.bookshop.book_shop_management.entity.React;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUserSaveDTO {
    private String userName;
    private String password;
    private String userContact;
}
