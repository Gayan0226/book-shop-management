package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.entity.React;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReactRepo extends JpaRepository<React, Integer> {
    @Query(value = "SELECT COUNT(*) FROM react b WHERE book_id = ?1 AND b.react = 1", nativeQuery = true)
    int getLikeCount(String isbn);
    @Query(value = "SELECT COUNT(*) FROM react b WHERE book_id = ?1 AND b.react = 0", nativeQuery = true)
    int getDisLikeCount(String isbn);
}
