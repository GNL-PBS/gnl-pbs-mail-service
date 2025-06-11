package com.osm.gnl.pbs.mail.gnl_pbs_mail_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.gnlsystems.com");
        mailSender.setPort(587);
        mailSender.setUsername("ogsg_ippms_notif@gnlsystems.com");
        mailSender.setPassword("OgsgPay123456&");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
