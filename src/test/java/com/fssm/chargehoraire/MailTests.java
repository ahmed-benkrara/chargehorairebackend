package com.fssm.chargehoraire;

import com.fssm.chargehoraire.Services.EmailSenderService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.EventListener;

@SpringBootTest
public class MailTests {
    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    @EventListener(ApplicationReadyEvent.class)
    void email() throws MessagingException {
        emailSenderService.sendEmail("ahmed.benkrara11@gmail.com", "test", "<a href='www.facebook.com'>test link</a>");
    }
}
