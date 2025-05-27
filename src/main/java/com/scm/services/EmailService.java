package com.scm.services;
import java.io.File;
import java.io.InputStream;

public interface EmailService {

    // send email API
    void sendEmail(String to, String subject, String body);

    void sendEmail(String[] to, String subject, String body);

    // With HTML API 
    void sendEmailWithHtml(String to , String subject, String htmlContent);

    // With attachment
    void sendEmailWithAttachment(String to, String subject, String body, File file);

    void sendEmailWithAttachment(String to, String subject, String body, InputStream is);


}
