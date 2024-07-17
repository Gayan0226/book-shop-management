package com.bookshop.book_shop_management.dto.request;

import com.bookshop.book_shop_management.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaveBookDTO {
    String isbnId;
    String category;
    String bookName;


}
