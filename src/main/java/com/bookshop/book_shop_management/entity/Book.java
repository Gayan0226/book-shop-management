package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Pattern(regexp = "^[a-zA-Z]+$", message = "ISBN can only contain simple and capital letters")    @Column(name = "isbn")
    String isbnId;
    @Column(name = "book_category")
    String category;
    @Column(name = "book_name", length = 100, nullable = false)
    String bookName;
    @Column(name = "author_name")
    String authorName;
    //get the foriegn  key from author table
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book(String isbnId, String category, String bookName) {
        this.isbnId = isbnId;
        this.category = category;
        this.bookName = bookName;
    }
}
