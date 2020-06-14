package poly.com.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poly.com.entity.OwnApartment;
import poly.com.helper.FileHelper;
import poly.com.repository.OwnApartmentRepository;

@Service
public class OwnApartmentService {

	@Autowired
	OwnApartmentRepository ownApmtRepository;
	
	@Autowired
	FileHelper fileHelper;

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
    public ResponseEntity<?> createOwn(OwnApartment ownApartment) {   	
        try {     	
        	if (ownApmtRepository.existsByPhone(ownApartment.getPhone())) 
       		 	return new ResponseEntity<>("Phone number already exists!", HttpStatus.CONFLICT);
        	
        	if (ownApmtRepository.existsByIdentitycard(ownApartment.getIdentitycard())) 
        		return new ResponseEntity<>("Identitycard number already exists!", HttpStatus.CONFLICT);
    
        	ownApartment.setId(0);      	
        	ownApartment = ownApmtRepository.save(ownApartment);
            return ResponseEntity.ok(ownApartment);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------ Update --------------------------------->
    public ResponseEntity<?> updateOwn(int id, OwnApartment ownApartment) {
        try {
            if (!ownApmtRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            OwnApartment checkPhone =  ownApmtRepository.findByPhone(ownApartment.getPhone()).orElse(null);
            if (checkPhone != null && checkPhone.getId() != id) 
            	return new ResponseEntity<>("Phone number already exists!", HttpStatus.CONFLICT);
            
            OwnApartment checkIdentitycard =  ownApmtRepository.findByIdentitycard(ownApartment.getIdentitycard()).orElse(null);
            if (checkIdentitycard != null && checkIdentitycard.getId() != id) 
            	return new ResponseEntity<>("Identitycard number already exists!", HttpStatus.CONFLICT);
                  
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
        	 OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);
             if (ownApartment == null) 		
            	 return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        	ownApmtRepository.deleteById(id);
        	fileHelper.deleteFile(ownApartment.getImage());
            return ResponseEntity.ok("delete success");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
    // < ------------------------------- Upload file ----------------------------------->
    public ResponseEntity<OwnApartment> uploadFile(MultipartFile mFile, int id){
    	if (mFile.isEmpty()) 
    		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    	
    	try {
    		OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);
    		if (ownApartment == null) 
    			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			String fileName =  fileHelper.saveFile(mFile, "user" + id); 
			ownApartment.setImage(fileName);
			ownApartment = ownApmtRepository.save(ownApartment);
			return ResponseEntity.ok(ownApartment);
		} catch (IOException e) {
			 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    
    
}