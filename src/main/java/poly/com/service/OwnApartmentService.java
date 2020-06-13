package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.OwnApartment;
import poly.com.repository.OwnApartmentRepository;

@Service
public class OwnApartmentService {

	@Autowired
	OwnApartmentRepository ownApmtRepository;
	

    // < --------------------------- find All -------------------------->
    public ResponseEntity<List<OwnApartment>> findAll() {
        List<OwnApartment> ownApartment = ownApmtRepository.findAll();
        return ResponseEntity.ok(ownApartment);
    }

    // < -------------------------- find by Id ---------------------------->
    public ResponseEntity<OwnApartment> findById(int id) {
        try {
        	OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);
            return ResponseEntity.ok(ownApartment);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < --------------------------- Create ---------------------------------->
    public ResponseEntity<OwnApartment> createOwn(OwnApartment ownApartment) {
        try { // check Identitycard unique
        	if (ownApmtRepository.existsByIdentitycard(ownApartment.getIdentitycard())) 
        		 return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            
        	ownApartment.setId(0);
        	ownApartment = ownApmtRepository.save(ownApartment);
            return ResponseEntity.ok(ownApartment);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------ Update --------------------------------->
    public ResponseEntity<OwnApartment> updateOwn(int id, OwnApartment ownApartment) {
        try {
            if (!ownApmtRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            if (ownApmtRepository.existsByIdentitycard(ownApartment.getIdentitycard())) 
       		 return new ResponseEntity<>(null, HttpStatus.CONFLICT);

            ownApartment.setId(id);
            ownApartment = ownApmtRepository.save(ownApartment);
            return ResponseEntity.ok(ownApartment);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------- Delete ----------------------------------->
    public ResponseEntity<String> deleteOwn(int id) {
        try {
        	 if (!ownApmtRepository.existsById(id))
                 return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        	 ownApmtRepository.deleteById(id);
            return ResponseEntity.ok("delete success");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}