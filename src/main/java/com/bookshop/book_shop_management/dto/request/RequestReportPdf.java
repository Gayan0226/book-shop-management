package com.bookshop.book_shop_management.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestReportPdf {
    String isbnName;
    String category;
    String bookTitle;
}
