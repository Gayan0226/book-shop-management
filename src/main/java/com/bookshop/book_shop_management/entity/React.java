package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "react")
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reactId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book bookReact;

}
