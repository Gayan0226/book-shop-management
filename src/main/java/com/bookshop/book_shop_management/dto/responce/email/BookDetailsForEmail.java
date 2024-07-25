package com.bookshop.book_shop_management.dto.responce.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsForEmail {
    private String bookId;
    private String bookName;
    private Long reactCount;
}
