package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Column(name = "createTime")
    private LocalDateTime createTime;

    @Column(name = "updateTime")
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        if (createTime == null) {
            createTime = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (updateTime == null || updateTime.isBefore(LocalDateTime.now())) {
            updateTime = LocalDateTime.now();
        }
    }
    public React(User userId, Book bookReact, boolean react) {
        this.userId = userId;
        this.bookReact = bookReact;
        this.react = react;
    }
}
