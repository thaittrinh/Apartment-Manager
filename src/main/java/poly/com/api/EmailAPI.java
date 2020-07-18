package poly.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.com.service.MailService;

@RestController
@RequestMapping("/api/email")
public class EmailAPI {

	@Autowired
	MailService mailService;
	
	  @PostMapping()
	  public ResponseEntity<?> sendEmail(@RequestParam("to") String sendTo, @RequestParam("subject") String subject,
			  							@RequestParam("body") String body) {	 	  
		  return mailService.sendEmail(sendTo, subject, body); 
	  }
	
}
