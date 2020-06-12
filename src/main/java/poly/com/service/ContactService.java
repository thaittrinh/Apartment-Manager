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

// < -------------------------------- Class Contact Service -----------------------------------------> 

	@Autowired
	ContactRepository contactRepository;
// -----------------------------------------------------

	
	// < ---------------------- findAll -------------------------->
	public ResponseEntity<List<Contact>> findAll() {
		List<Contact> contacts = contactRepository.findAll();
		return ResponseEntity.ok(contacts);
	}

	// < ---------------------- findById -------------------------->
	public ResponseEntity<Contact> findById(int id) {
		try {
			Contact contacts = contactRepository.findById(id).orElse(null);	
			return ResponseEntity.ok(contacts);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------- Create ----------------------------->
	public ResponseEntity<Contact> createContact(Contact contact) {
		try {
			contact.setId(0);
			Contact contacts = contactRepository.save(contact);
			return ResponseEntity.ok(contacts);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------- Update ---------------------------------->
	public ResponseEntity<Contact> updateContact(int id, Contact contact) {
		try {
			Contact contacts = contactRepository.findById(id).orElse(null);
			if (contacts == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			contact.setId(id);
			contacts = contactRepository.save(contact);
			return ResponseEntity.ok(contacts);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < -------------------------- Delete ------------------------------->
	public ResponseEntity<String> deleteContact(int id) {
		try {		
			if (!contactRepository.existsById(id))
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			contactRepository.deleteById(id);
			return ResponseEntity.ok("Xóa Thành Công");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
