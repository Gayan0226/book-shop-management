package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AuthorREPO extends JpaRepository<Author,Integer>{
}
