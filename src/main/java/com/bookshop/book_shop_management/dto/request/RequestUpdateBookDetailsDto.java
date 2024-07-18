package com.bookshop.book_shop_management.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateBookDetailsDto {
    String category;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "TItle must contain only alphanumeric characters.")
    String bookTitle;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]+$", message = "only contains simple and capital letters")
    String authorName;
}
