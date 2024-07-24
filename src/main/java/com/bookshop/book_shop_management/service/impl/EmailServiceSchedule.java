package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.responce.ResponseToEmail;
import com.bookshop.book_shop_management.exceptions.NotFoundException;
import com.bookshop.book_shop_management.service.EmailService;
import com.bookshop.book_shop_management.service.ReactService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailServiceSchedule {

    private final EmailService emailService;

    private final ReactService reactService;

    private static final Logger log = LoggerFactory.getLogger(EmailServiceSchedule.class);
//TODO : Error is Coming  runTime Fixed IT
    @Scheduled(fixedRate = 30000)
    public void sendMail() {
        log.info("Run Method email");
        List<ResponseToEmail> reacts = reactService.getEmailForSendMail();
        if (!reacts.isEmpty()) {
            for (int i = 0; i < reacts.size(); i++) {
//            emailService.sendMail(reacts.get(i).getEmailAuthor(),"Inform React Count", "your Books React count is : " + reacts.get(i).getReactCountToEmail());
                log.info("Send Email !{}", reacts.get(i).getReactCountToEmail());
            }
        } else {
            log.info("Database Not Data");
            throw new NotFoundException("Not Found Any Email !");
        }
    }
}
