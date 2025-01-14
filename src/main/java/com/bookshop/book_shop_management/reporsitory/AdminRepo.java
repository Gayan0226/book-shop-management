package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin,Integer> {
    @Query("select  a from  Admin a where a.userName=?1")
    Admin findAdminByUserName(String username);
}
