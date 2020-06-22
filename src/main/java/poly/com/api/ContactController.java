package poly.com.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.com.constant.URL_API;
import poly.com.dto.ResponseDTO;
import poly.com.entity.Contact;
import poly.com.service.ContactService;

@RestController
@RequestMapping(URL_API.CONTACT)
public class ContactController {

// <---------------------------- Class  Contact RestController ------------------------------------> 
	@Autowired
	ContactService contactService;


	// < ------------------- findById ----------------------->
	@GetMapping()
	public ResponseEntity<ResponseDTO> findByMaxId() {
		return contactService.findByMaxId();
	}

    // < ---------------------- Update -------------------------> 
	@PutMapping()
	public ResponseEntity<ResponseDTO> updateContact(@Valid @RequestBody Contact contact) {
		return contactService.updateContact(contact);
	}
  
}
