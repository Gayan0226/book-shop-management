package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.dto.responce.RequestAllBookByCategory;
import com.bookshop.book_shop_management.dto.responce.ResponseBookSearchByAuthorEmail;
import com.bookshop.book_shop_management.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
@Transactional
public interface BookREPO extends JpaRepository<Book, String> {
    @Modifying
    @Query(value = "update book b set b.book_name=?1,b.book_category=?2 where b.book_id =?3", nativeQuery = true)
    void updateBookDetails(String bookName, String category, String bookId);

    @Query(value = "SELECT * FROM book b WHERE b.book_id  LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    Page<Book> findAllSearch(String isbn, Pageable pageable);
@Query(value = "SELECT b.book_id AS isbnId, b.book_category AS category,b.book_name AS bookTitle FROM book b JOIN author a ON b.author_id =a.author_id where a.email=?1 ",nativeQuery = true)
    Page<ResponseBookSearchByAuthorEmail> findSearchBookByEmail(String email, Pageable pageable);
    @Query(value = "SELECT b.book_id AS isbnId, b.book_name AS bookTitle,a.first_name As authorName ,b.book_category AS category FROM book b JOIN author a ON b.author_id =a.author_id ORDER BY b.book_category desc ", nativeQuery = true)
    Page<RequestAllBookByCategory> findAllBooks(Pageable pageable);
}
