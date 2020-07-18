package poly.com.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import poly.com.constant.MessageError;
import poly.com.dto.ResponseDTO;

@Service
public class MailService {

   @Autowired
   public JavaMailSender emailSender;
    
   public ResponseEntity<?> sendEmail(  String sendTo, String subject ,String body) {
	   
	   try {
		   MimeMessage message = emailSender.createMimeMessage();		   
		   boolean multipart = true; 
	        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
	        String htmlMsg = 
	                "<img src='https://drive.google.com/uc?id=18u5te2KfgdFjkbH01JtR27iha0UNOwi4' width='240' hight='240'>"
	        		+ body;
	        		        
	        message.setContent(htmlMsg, "text/html; charset=UTF-8");      
	        helper.setTo(sendTo);
	        helper.setSubject(subject);
	        this.emailSender.send(message);
		   
		     return ResponseEntity.ok("success!");
		} catch (Exception e) {
			 return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}     
	   
   }
   
   
}
