package com.bookshop.book_shop_management.entity;

import com.bookshop.book_shop_management.entity.enums.BookCategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    String isbnId;
    @Enumerated(EnumType.STRING)
    @Column(name = "book_category",nullable = false)
    BookCategoryType category;
    @Column(name = "book_name", length = 100, nullable = false)
    String bookTitle;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @OneToMany(mappedBy = "bookReact")
    private Set<React> react;
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

    public Book(String isbnId, BookCategoryType category, String bookTitle, Author author) {
        this.isbnId = isbnId;
        this.category = category;
        this.bookTitle = bookTitle;
        this.author = author;
    }
}
