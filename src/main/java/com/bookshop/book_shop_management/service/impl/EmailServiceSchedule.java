package com.bookshop.book_shop_management.service.impl;

import com.bookshop.book_shop_management.dto.responce.ResponseToEmail;
import com.bookshop.book_shop_management.dto.responce.email.BookDetailsForEmail;
import com.bookshop.book_shop_management.service.EmailService;
import com.bookshop.book_shop_management.service.ReactService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailServiceSchedule {

    private final EmailService emailService;

    private final ReactService reactService;

    private static final Logger log = LoggerFactory.getLogger(EmailServiceSchedule.class);

    @Scheduled(fixedRate = 30000)
    public void sendMail() {
        try {
            log.info("Run Method email");
            List<ResponseToEmail> reacts = reactService.getEmailForSendMail();
            Map<String, List<BookDetailsForEmail>> map = new HashMap<>();
            for (ResponseToEmail r : reacts) {
                String emailAuthor = r.getEmailAuthor();
                BookDetailsForEmail books = new BookDetailsForEmail(
                        r.getBookID(),
                        r.getBookName(),
                        r.getReactCountToEmail()
                );
                map.computeIfAbsent(emailAuthor, k -> new ArrayList<>()).add(books);
            }
            for (Map.Entry<String, List<BookDetailsForEmail>> entry : map.entrySet()) {
                String emailAuthor = entry.getKey();
                List<BookDetailsForEmail> books = entry.getValue();
                String subjectEmail = "Inform Your Books React Count !\n";
                String bodyEmail = "Dear Author,\nHere are the react counts for your books:\n";
                for (BookDetailsForEmail book : books) {
                    bodyEmail += String.format("\nBookID: %s\n,\nBook Name: %s\t,React Count: %d\n",
                            book.getBookId(), book.getBookName(), book.getReactCount());
                }
                log.info("Email Subject: {} Body {} ", subjectEmail,bodyEmail);
                emailService.sendMail(emailAuthor, subjectEmail, bodyEmail);
            }




        } catch (
                Exception e) {
            log.error(e.getMessage());
        }
    }
}
