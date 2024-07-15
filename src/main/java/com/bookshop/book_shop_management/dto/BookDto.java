package com.bookshop.book_shop_management.dto;

import com.bookshop.book_shop_management.entity.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    String category;
    String name;
    double autorName;
    private Author author;
}
