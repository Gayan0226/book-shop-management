package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;
    @OneToMany(mappedBy = "user")
    private Set<React> react;
}
