package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.dto.responce.ResponseOrderBookByReact;
import com.bookshop.book_shop_management.entity.React;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


@Query(value = "SELECT r.book_id AS isbnId,COUNT(r.react)AS reactCount ,a.first_name AS firstName,a.email AS email ,a.author_contact AS contact From react r JOIN book b ON r.book_id=b.book_id JOIN author a ON b.author_id =a.author_id where r.react=1 Group By r.book_id  ORDER BY reactCount desc ",nativeQuery = true)
    Page<ResponseOrderBookByReact> findAllBookByReactOrder(Pageable pageable);
}
/*
SELECT r.book_id AS bookId, COUNT(r.react_id) AS reactCount " +
                   "FROM react r " +
                   "WHERE r.react = 1 " +
                   "GROUP BY r.book_id " +
                   "ORDER BY reactCount DESC"

*/
