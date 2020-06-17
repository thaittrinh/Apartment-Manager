package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.Apartment;
import poly.com.repository.ApartmentRepository;
import poly.com.repository.OwnApartmentRepository;

@Service
public class ApartmentService {

	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	OwnApartmentRepository ownApartmentRepository;
	
    // < -------------------- find All ---------------------------->
    public ResponseEntity<List<Apartment>> findAll() {	
        List<Apartment> apartments = apartmentRepository.findAll();
        return ResponseEntity.ok(apartments);
    }

    // < -------------------- find by Id ---------------------------->
    public ResponseEntity<Apartment> findById(String id) {
        try {
        	Apartment apartment = apartmentRepository.findById(id).orElse(null);    
            return ResponseEntity.ok(apartment);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ---------------------------- Create ------------------------->
 
    public ResponseEntity<Apartment> createApartment(Apartment apartment) {
        try { 
        	// Id already exists  
        	Apartment apartment2 = apartmentRepository.findById(apartment.getId()).orElse(null);
        	if (apartment2 != null)
        		 return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        	
        	 if ( apartment.getOwnApartment() != null &&  !ownApartmentRepository.existsById(apartment.getOwnApartment().getId()))
                 return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        	apartment = apartmentRepository.save(apartment);
            return ResponseEntity.ok(apartment);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ----------------------------- update ------------------------- 
    public ResponseEntity<?> updateApartment(String id, Apartment apartment) {
        try {
            if (!apartmentRepository.existsById(id))
                return new ResponseEntity<>( id + " không tồn tại", HttpStatus.NOT_FOUND);
            
            if ( apartment.getOwnApartment() != null &&  !ownApartmentRepository.existsById(apartment.getOwnApartment().getId()))
                  return new ResponseEntity<>("Chủ căn hộ không tồn tại", HttpStatus.NOT_FOUND);

            apartment.setId(id);
            apartment = apartmentRepository.save(apartment);
            return ResponseEntity.ok(apartment);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ----------------------------- Delete -------------------------->
    public ResponseEntity<String> deleteApartment(String id) {
        try {
        	if (!apartmentRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            
        	apartmentRepository.deleteById(id);
            return ResponseEntity.ok("Delete success");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
}
