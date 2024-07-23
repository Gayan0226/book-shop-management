package com.bookshop.book_shop_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
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
    int authorId;
    @Column(name = "first_name", length = 100, nullable = false)
    String firstName;
    @Column(name = "last_name", length = 100, nullable = false)
    String lastName;
    @Column(name = "email", length = 100, nullable = false,unique = true)
    String email;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "author_contact", length = 20, nullable = false)
    ArrayList contact;
    //Create to foriecgn key
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books;
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

    public Author(String firstName, String lastName, String email, ArrayList contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
    }
}
