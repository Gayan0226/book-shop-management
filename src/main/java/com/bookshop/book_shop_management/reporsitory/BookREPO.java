package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BookREPO extends JpaRepository<Book, String> {

}
