package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "react")
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reactId;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User userId;
    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book bookReact;
    @Column(name ="react",columnDefinition = "TINYINT default 0")
    private boolean react;

    public React(User userId, Book bookReact, boolean react) {
        this.userId = userId;
        this.bookReact = bookReact;
        this.react = react;
    }
}
