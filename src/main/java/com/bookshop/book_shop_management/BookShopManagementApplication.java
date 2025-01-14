package com.bookshop.book_shop_management;

import net.sf.jasperreports.engine.JRException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class BookShopManagementApplication {

    public static void main(String[] args) throws JRException {
        SpringApplication.run(BookShopManagementApplication.class, args);
    }



}
