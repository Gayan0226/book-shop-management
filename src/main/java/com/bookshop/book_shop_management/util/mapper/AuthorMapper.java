package com.bookshop.book_shop_management.util.mapper;

import com.bookshop.book_shop_management.dto.request.SaveAuthorDTO;
import com.bookshop.book_shop_management.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author dtoToEntity(SaveAuthorDTO authorDTO);
}
