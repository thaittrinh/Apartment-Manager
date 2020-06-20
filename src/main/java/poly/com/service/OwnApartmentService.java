package poly.com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poly.com.dto.OwnApartmentDTO;
import poly.com.entity.Apartment;
import poly.com.entity.OwnApartment;
import poly.com.helper.FileHelper;
import poly.com.repository.ApartmentRepository;
import poly.com.repository.OwnApartmentRepository;

@Service
public class OwnApartmentService {

	@Autowired
	OwnApartmentRepository ownApmtRepository;
	
	@Autowired 
	ApartmentRepository apartmentRepository;
	
	@Autowired
	FileHelper fileHelper;

    // < --------------------------- find All convert to OwnApartmentDTO -------------------------->
	 public ResponseEntity<List<OwnApartmentDTO>> findAll() {
		 try {
			 List<OwnApartment> ownApartments =  ownApmtRepository.findAll();  
			    List<OwnApartmentDTO> ownDTOs = new ArrayList<>(); 
			    for (OwnApartment own : ownApartments) {	
			    	 OwnApartmentDTO ownDTO = convertToDTO(own);
			    	 ownDTOs.add(ownDTO);  	
				}
		    return ResponseEntity.ok(ownDTOs);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
	}
	
	
    // < -------------------------- find by Id ---------------------------->
    public ResponseEntity<OwnApartmentDTO> findById(int id) {
        try {	
        	OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);
            return ResponseEntity.ok(convertToDTO(ownApartment));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // < --------------------------- Create ---------------------------------->
    public ResponseEntity<?> createOwn(OwnApartmentDTO ownDTO) {   	  	
        try {  
        	// Phone number already exists
        	if (ownApmtRepository.existsByPhone(ownDTO.getPhone())) 
       		 	return new ResponseEntity<>("Số điện thoại đã tồn tại!", HttpStatus.CONFLICT);
        	// Identitycard number already exists
        	if (ownApmtRepository.existsByIdentitycard(ownDTO.getIdentitycard())) 
        		return new ResponseEntity<>("Số chứng minh đã tồn tại!", HttpStatus.CONFLICT);
        	
        	// Create new Own
        	OwnApartment ownApartment = convertToEntity(ownDTO);     
        	ownApartment.setId(0);    
            ownApartment = ownApmtRepository.save(ownApartment);
        	
            // Update id_own apartment
        	if(ownDTO.getApartments().size() > 0) {	
        		for (String idA : ownDTO.getApartments()) {
        			Apartment apartment = apartmentRepository.findById(idA).orElse(null);
        			// apartment does not exist
					if (apartment == null) {
						//Remove Own form database
						ownApmtRepository.delete(ownApartment);
						return new ResponseEntity<>(idA + " không tồn tại! ", HttpStatus.NOT_FOUND);
					}
			      //else
					apartment.setOwnApartment(ownApartment);
					apartmentRepository.save(apartment);
				}
        	}
        	 OwnApartmentDTO ownApartmentDTO = convertToDTO(ownApartment);
        	
            return ResponseEntity.ok(ownApartmentDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------ Update --------------------------------->
    public ResponseEntity<?> updateOwn(int id, OwnApartmentDTO ownDTO) {
        try {
        	OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);	
            if (ownApartment == null)
                return new ResponseEntity<>("Người này không tồn tại!", HttpStatus.NOT_FOUND);

            OwnApartment checkPhone =  ownApmtRepository.findByPhone(ownDTO.getPhone()).orElse(null);
            if (checkPhone != null && checkPhone.getId() != id) 
            	return new ResponseEntity<>("Số điện thoại đã tồn tại!", HttpStatus.CONFLICT);
            
            OwnApartment checkIdentitycard =  ownApmtRepository.findByIdentitycard(ownDTO.getIdentitycard()).orElse(null);
            if (checkIdentitycard != null && checkIdentitycard.getId() != id) 
            	return new ResponseEntity<>("Số chứng minh đã tồn tại!", HttpStatus.CONFLICT);
            
           
    		for (String idA : ownDTO.getApartments()) {
    			Apartment apartment = apartmentRepository.findById(idA).orElse(null);
				if (apartment == null) 	
					return new ResponseEntity<>(idA + " không tồn tại! ", HttpStatus.NOT_FOUND);	    
			}
    	   
            OwnApartment newOwnApartment = convertToEntity(ownDTO); 
            newOwnApartment.setId(id);
            newOwnApartment.setImage(ownApartment.getImage());
            newOwnApartment = ownApmtRepository.save(newOwnApartment);
            
            List<String> listId = apartmentRepository.findIds(newOwnApartment);
            List<String> listIdNew = ownDTO.getApartments();
            for (int i = 0; i < listId.size(); i++) {
            	boolean chk = true; // mặc định ko tồn tại
				for (int j = 0; j < listIdNew.size(); j++) {
					if (listId.get(i).equals(listIdNew.get(j))) {
						chk = false; // tồn tại
					}
				}
				if (chk) {			
					Apartment apartment = apartmentRepository.findById(listId.get(i)).orElse(null);
					apartment.setOwnApartment(null); // update id_own null				
					apartmentRepository.save(apartment);
				}
				
			}
            
            for (int i = 0; i < listIdNew.size(); i++) {
            	boolean chk = true; // mặc định ko tồn tại
				for (int j = 0; j < listId.size(); j++) {
					if (listIdNew.get(i).equals(listId.get(j))) {
						chk = false; // tồn tại
					}
				}
				if (chk) {
					Apartment apartment = apartmentRepository.findById(listIdNew.get(i)).orElse(null);
					apartment.setOwnApartment(newOwnApartment);// update id_own					
					apartmentRepository.save(apartment);
				}
				
			}
            
                       
            OwnApartmentDTO ownApartmentDTO = convertToDTO(newOwnApartment);
            return ResponseEntity.ok(ownApartmentDTO);
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
			
    		fileHelper.deleteFile(ownApartment.getImage());
			String fileName =  fileHelper.saveFile(mFile, "user" + id); 
			ownApartment.setImage(fileName);
			ownApartment = ownApmtRepository.save(ownApartment);	
			return ResponseEntity.ok(ownApartment);
		} catch (IOException e) {
			 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    public OwnApartmentDTO convertToDTO(OwnApartment own) {
    	return new OwnApartmentDTO(own.getId(), own.getFullname(), own.getGender(), own.getHomeTown(),
								   own.getPhone(), own.getEmail(), own.getBirthday(), own.getJob(),
								   own.getImage(), own.getIdentitycard(), apartmentRepository.findIds(own));
    }
    
    public OwnApartment convertToEntity(OwnApartmentDTO ownDTO) {
    	return new OwnApartment(ownDTO.getId(),ownDTO.getFullname(), ownDTO.getGender(), ownDTO.getBirthday(), 
    							ownDTO.getJob(), ownDTO.getPhone(), ownDTO.getEmail(), ownDTO.getHomeTown(),
    							ownDTO.getImage(), ownDTO.getIdentitycard());
    }
   
   
    
}