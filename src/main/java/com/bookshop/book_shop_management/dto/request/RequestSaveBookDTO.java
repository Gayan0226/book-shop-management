package com.bookshop.book_shop_management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaveBookDTO {

    @NotEmpty(message = "ISBN ID is Required")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ISBN must contain only alphanumeric characters.")
    @Schema(example = "contain only alphanumeric characters")
    String isbnId;
    @NotEmpty
    String category;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Title must contain only alphanumeric characters.")
    @Schema(example = "contain only alphanumeric characters")
    String bookTitle;


}
