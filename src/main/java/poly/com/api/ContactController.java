package poly.com.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import poly.com.entity.Contact;
import poly.com.service.ContactService;

@RestController
@RequestMapping("api/contact")
public class ContactController {

	@Autowired
	ContactService contactService;
	
	@GetMapping()
	public ResponseEntity<List<Contact>> findAll()
	{
		return contactService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contact> findById(@PathVariable int id)
	{
		
			return contactService.findById(id);
	}
	
	@PostMapping()
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact)
	{
		return contactService.createContact(contact);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contact> updateContact (@PathVariable int id, @RequestBody Contact contact)
	{
		return contactService.updateContact(id, contact);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable int id)
	{
		return contactService.deleteContact(id);
	}
}
