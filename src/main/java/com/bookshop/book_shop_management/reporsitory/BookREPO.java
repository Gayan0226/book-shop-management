package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
@Transactional
public interface BookREPO extends JpaRepository<Book, String> {

    @Modifying
    @Query(value = "update book b set b.book_name=?1, b.author_name=?2 ,b.book_category=?3 where b.book_id =?4", nativeQuery = true)
    void updateBookDetails(String bookName, String authorName, String category, String bookId);

    Page<Book> findAllByCategoryEquals(String category, Pageable pageable);

    Optional<Book> findFirstByCategoryEquals(String category);

    @Query(value = "SELECT * FROM book b WHERE b.book_id  LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    Page<Book> findAllSea(String isbn, Pageable pageable);
}
