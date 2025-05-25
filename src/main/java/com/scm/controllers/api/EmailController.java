package com.scm.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.scm.helpers.CustomResponse;
import com.scm.helpers.EmailRequest;
import com.scm.services.EmailService;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    //send email
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        //TODO: process POST request
        emailService.sendEmailWithHtml(request.getTo(),request.getSubject(),request.getMessage());
        return ResponseEntity.ok(
            CustomResponse.builder().message("Email Send Successfully!!").httpStatus(HttpStatus.OK).success(true).build()
        );
        
    }
    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file) throws IOException{
        //TODO: process POST request
        emailService.sendEmailWithAttachment(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());
        return ResponseEntity.ok(
            CustomResponse.builder().message("Email Send Successfully!!").httpStatus(HttpStatus.OK).success(true).build()
        );
        
    }
    
    

}
