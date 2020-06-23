package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.constant.MessageError;
import poly.com.constant.MessageSuccess;
import poly.com.dto.ResponseDTO;
import poly.com.entity.Contact;
import poly.com.repository.ContactRepository;

@Service
public class ContactService {

// < -------------------------------- Class Contact Service -----------------------------------------> 

	@Autowired
	ContactRepository contactRepository;

	// < ---------------------- findById -------------------------->
	public ResponseEntity<ResponseDTO> findByMaxId() {
		try {
			Contact contact = contactRepository.findTopByOrderByIdDesc().orElse(null);	
			return ResponseEntity.ok(new ResponseDTO(contact,null));
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------- Update ---------------------------------->
	public ResponseEntity<ResponseDTO> updateContact(Contact contact) {
		try {
			List<Contact> list = contactRepository.findAll();
			if (list.size() < 1) {
				contact = contactRepository.save(contact);
			}else {
				Contact contactOld = contactRepository.findTopByOrderByIdDesc().orElse(null);	
				if (contactOld == null)
					return new ResponseEntity<>(new ResponseDTO(contact,MessageError.ERROR_404_CONTACT), HttpStatus.NOT_FOUND);
				
				contact.setId(contactOld.getId());
				contact = contactRepository.save(contact);		
			}

			return ResponseEntity.ok(new ResponseDTO(contact,MessageSuccess.UPDATE_SUCCSESS));
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
