package com.stockservice.stockservice.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EmailServiceImpl implements EmailService{

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.fromuser}")
    private String fromUser;

    @Value("${spring.mail.displayname}")
    private String displayName;


    private final JavaMailSender javaMailSender;

    public void sendHtmlMessage(String to, String subject, String html, List<File> attachments) {
        try {
            log.debug("<sendHtmlMessage> method is STARTED. Parameters To: {} , Subject: {}", to, subject);
            if (to == null || subject == null || html == null)
                throw new RuntimeException("Input parameters cannot be null. Method <sendHtmlMessage>");
            if (to.startsWith("123")) {
                return;
            }
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setText(html, true); // Use this or above line.
            helper.setTo(to);
            helper.setSubject(subject);
            if (attachments != null) {
                attachments.stream().forEach(f -> {
                    try {
                        helper.addAttachment(f.getName(), f);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            javaMailSender.send(mimeMessage);
            log.debug("<sendHtmlMessage> method FINISHED.");
        } catch (Exception e) {
            log.error("Error in <sendHtmlMessage> method, for email address: " + to + "\n Error message: " + e);
        }

    }
}
