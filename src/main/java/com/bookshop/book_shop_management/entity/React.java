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
    private long reactId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book bookReact;
    @Column(name ="react",columnDefinition = "TINYINT default 0")
    private boolean react;

}
