package com.bookshop.book_shop_management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUserToReactBookDTO {
    private boolean react;
    @NotEmpty(message = "ISBN ID is Required")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ISBN must contain only alphanumeric characters.")
    @Schema(example = "Contain only alphanumeric characters")
    private String bookReact;

}
