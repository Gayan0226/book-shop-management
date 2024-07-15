package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    String id;
    @Column(name = "book_category")
    String category;
    @Column(name = "book_name", length = 100, nullable = false)
    String name;
    @Column(name = "author_name")
    double autorName;
    //get the foriegn  key from author table
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

}
