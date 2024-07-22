package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.responce.ResponseOrderBookByReact;
import com.bookshop.book_shop_management.reporsitory.AuthorREPO;
import com.bookshop.book_shop_management.reporsitory.BookREPO;
import com.bookshop.book_shop_management.reporsitory.ReactRepo;
import com.bookshop.book_shop_management.service.EmailService;
import com.bookshop.book_shop_management.service.ReactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailServiceSchedule {
    @Autowired
    private EmailService emailService;
    @Autowired
    private ReactService reactService;

    private static final Logger log = LoggerFactory.getLogger(EmailServiceSchedule.class);

    @Scheduled(fixedRate = 30000)
    public void sendMail() throws InterruptedException {
        List<ResponseOrderBookByReact> reacts = reactService.getEmailForSendMail();
        for (int i = 0; i < reacts.size(); i++) {
            log.info("send Email "+reacts.get(i).getEmail());
            Thread.sleep(200);
            System.out.println(reacts.get(i).getEmail());
            System.out.println(reacts.get(i).getReactCount());
            emailService.sendMail(reacts.get(i).getEmail(), reacts.get(i).getFirstName(), "your Books React count is : " + reacts.get(i).getReactCount());
            Thread.sleep(3000);
        }
    }
}
