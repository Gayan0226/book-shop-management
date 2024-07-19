package com.bookshop.book_shop_management.reporsitory;

import com.bookshop.book_shop_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
