package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id", length = 4)
    int id;
    @Column(name = "first_name", length = 100, nullable = false)
    String firstName;
    @Column(name = "last_name", length = 100, nullable = false)
    String lastName;
    @Column(name = "email", length = 100, nullable = false)
    String email;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "author_contact", length = 20, nullable = false)
    ArrayList contact;
    //Create to foriecgn key
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books;

}
