package com.bookshop.book_shop_management.dto;

import com.bookshop.book_shop_management.entity.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDTO {
    int authorId;
    String firstName;
    String lastName;
    String email;
    ArrayList contact;
    private Set<Book> books;
}
