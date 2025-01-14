package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id", length = 4)
    private int adminId;
    private String userName;
    private String password;

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
