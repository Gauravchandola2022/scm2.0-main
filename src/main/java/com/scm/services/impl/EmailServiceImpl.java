package com.scm.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.scm.services.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender eMailSender;

    

    @Value("${spring.mail.properties.domain_name}")
    private String domainName;

    @Override
    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(domainName);
        eMailSender.send(message);
        

    }
    public void sendEmail(String[] to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(domainName);
        eMailSender.send(message);

    }


    @Override
    public void sendEmailWithHtml(String to , String subject, String htmlContent) {
        // TODO Auto-generated method stub
        MimeMessage message = eMailSender.createMimeMessage();
        try{
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(domainName);
        helper.setText(htmlContent,true);
        eMailSender.send(message);
        } catch(MessagingException e){
            throw new RuntimeException(e);
        }
        
       
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, File file) {
        // TODO Auto-generated method stub
       MimeMessage message = eMailSender.createMimeMessage();
        try{
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(domainName);
        helper.setText(body);
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment(fileSystemResource.getFilename(),file);
        eMailSender.send(message);
        } catch(MessagingException e){
            throw new RuntimeException(e);
        }
        
       
    }


    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, InputStream is) {
        // TODO Auto-generated method stub
       MimeMessage message = eMailSender.createMimeMessage();
        try{
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(domainName);
        helper.setText(body, true);
        File file = new File("src/main/resources/email/test.png");
        // Files.copy(is,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment(fileSystemResource.getFilename(),file);
        eMailSender.send(message);
        } catch(MessagingException e){
            throw new RuntimeException(e);
        }
        
       
    }


}
