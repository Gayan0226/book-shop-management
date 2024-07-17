package com.bookshop.book_shop_management.dto.request;

import com.bookshop.book_shop_management.entity.Author;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.aspectj.bridge.IMessage;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaveBookDTO {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]+$\n", message = "(only contains simple and capital letters")
    String isbnId;
    String category;
    String bookName;


}
