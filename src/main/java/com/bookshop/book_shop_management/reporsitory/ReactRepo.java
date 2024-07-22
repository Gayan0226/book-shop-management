package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.entity.React;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface ReactRepo extends JpaRepository<React, Integer> {
    @Query(value = "SELECT COUNT(*) FROM react b WHERE book_id = ?1 AND b.react = 1", nativeQuery = true)
    int getLikeCount(String isbn);

    @Query(value = "SELECT COUNT(*) FROM react b WHERE book_id = ?1 AND b.react = 0", nativeQuery = true)
    int getDisLikeCount(String isbn);

    @Modifying
    @Query(value = "update  react r set r.react=?1 where r.book_id=?2 And r.react_id=?3", nativeQuery = true)
    int updateValue(boolean react, String isbn, int reactId);
}
