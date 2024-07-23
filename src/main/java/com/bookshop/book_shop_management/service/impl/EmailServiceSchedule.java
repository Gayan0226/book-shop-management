package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.responce.ResponseToEmail;
import com.bookshop.book_shop_management.service.EmailService;
import com.bookshop.book_shop_management.service.ReactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

  /*  @Scheduled(fixedRate = 3000)
    public void sendMail() {
        List<ResponseToEmail> reacts = reactService.getEmailForSendMail();

        for (int i = 0; i < reacts.size(); i++) {
//            emailService.sendMail(reacts.get(i).getEmailAuthor(),"Inform React Count", "your Books React count is : " + reacts.get(i).getReactCountToEmail());
            log.info("Send Email !{}", reacts.get(i).getReactCountToEmail());
        }
    }*/
}
