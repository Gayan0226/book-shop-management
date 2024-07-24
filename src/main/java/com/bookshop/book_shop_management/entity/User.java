package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Column(name = "user_contact")
    private String userContact;
    @OneToMany(mappedBy = "userId")
    private Set<React> react;
}
