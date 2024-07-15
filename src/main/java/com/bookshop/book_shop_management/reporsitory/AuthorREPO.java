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
@Transactional
public interface AuthorREPO extends JpaRepository<Author, Integer> {
    @Modifying
    @Query(value = "Update author a set a.first_name=?1,a.email=?2 where a.author_id=?3", nativeQuery = true)
    void  updateNameContactsById(String firstName, String email, int id);



}
