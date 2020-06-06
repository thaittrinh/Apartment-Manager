package poly.com.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.Contact;
import poly.com.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepository;
	
	public ResponseEntity<List<Contact>> findAll()
	{
		List<Contact> contacts = contactRepository.findAll();
		return ResponseEntity.ok(contacts);
	}
	
	public ResponseEntity<Contact> findById(int id)
	{
		Contact contacts =null;
		try {
			 contacts = contactRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(contacts);
	}
	public ResponseEntity<Contact> createContact(Contact contact)
	{
		Contact contacts = null;
		contact.setId(0);
		try {
			contacts = contactRepository.save(contact);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(contacts);
	}
	
	public ResponseEntity<Contact> updateContact(int id,Contact contact)
	{
		Contact contacts ;
		contacts = contactRepository.findById(id).orElse(null);
		if(contacts == null)
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
		try {
			contacts.setId(id);
			contacts = contactRepository.save(contact);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(contacts);
	}
	
	public ResponseEntity<String> deleteContact(int id)
	{
		Contact contacts = contactRepository.findById(id).orElse(null);
		
		if(contacts == null)
		{
			   return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
		try {
			contactRepository.deleteById(id);;
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("Xóa Thành Công");
	}
}
