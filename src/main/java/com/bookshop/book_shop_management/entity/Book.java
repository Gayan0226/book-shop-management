package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    String id;
    @Column(name = "book_name", length = 100, nullable = false)
    String name;
    @Column(name = "book_catagory")
    String catagory;
    @Column(name = "price")
    double price;
    //get the foriegn  key from author table


}
