package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id", length = 4)
    int id;
    @Column(name = "author_name", length = 100, nullable = false)
    String name;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "author_phone", length = 20, nullable = false)
    ArrayList phone;
//Create to foriegn key

}
