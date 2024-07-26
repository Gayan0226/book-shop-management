package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@EnableJpaRepositories

public interface AuthorREPO extends JpaRepository<Author, Integer> {

}
