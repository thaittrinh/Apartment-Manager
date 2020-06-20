package poly.com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poly.com.constant.MessageError;
import poly.com.constant.MessageSuccess;
import poly.com.dto.OwnApartmentDTO;
import poly.com.dto.ResponseDTO;
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
	 public ResponseEntity<ResponseDTO>  findAll() {
		 try {
			 List<OwnApartment> ownApartments =  ownApmtRepository.findAll();  
			    List<OwnApartmentDTO> ownDTOs = new ArrayList<>(); 
			    for (OwnApartment own : ownApartments) {	
			    	 OwnApartmentDTO ownDTO = convertToDTO(own);
			    	 ownDTOs.add(ownDTO);  	
				}
			    return ResponseEntity.ok(new ResponseDTO(ownDTOs, null));
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
	}
	
    // < -------------------------- find by Id ---------------------------->
    public ResponseEntity<ResponseDTO>  findById(int id) {
        try {	
        	OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);      
        	return ResponseEntity.ok(new ResponseDTO(convertToDTO(ownApartment), null));
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // < --------------------------- Create ---------------------------------->
    public ResponseEntity<ResponseDTO> createOwn(OwnApartmentDTO ownDTO) {   	  	
        try {  
        	// Phone number already exists
        	if (ownApmtRepository.existsByPhone(ownDTO.getPhone())) 
        		return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_409_PHONE), HttpStatus.CONFLICT);
        	// Identitycard number already exists
        	if (ownApmtRepository.existsByIdentitycard(ownDTO.getIdentitycard())) 
        		return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_409_IDENTICARD), HttpStatus.CONFLICT);
        	
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
						return new ResponseEntity<>(new ResponseDTO(null, idA + " không tồn tại! "), HttpStatus.NOT_FOUND);						
					}
			      //else
					apartment.setOwnApartment(ownApartment);
					apartmentRepository.save(apartment);
				}
        	}
        	 OwnApartmentDTO ownApartmentDTO = convertToDTO(ownApartment);
        	 
        	 return ResponseEntity.ok(new ResponseDTO(ownApartmentDTO, MessageSuccess.INSERT_SUCCSESS));
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------ Update --------------------------------->
    public ResponseEntity<ResponseDTO> updateOwn(int id, OwnApartmentDTO ownDTO) {
        try {
        	OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);	
            if (ownApartment == null)
            	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_OWN_APARTMENT), HttpStatus.NOT_FOUND);

            OwnApartment checkPhone =  ownApmtRepository.findByPhone(ownDTO.getPhone()).orElse(null);
            if (checkPhone != null && checkPhone.getId() != id) 
            	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_409_PHONE), HttpStatus.CONFLICT);
            
            OwnApartment checkIdentitycard =  ownApmtRepository.findByIdentitycard(ownDTO.getIdentitycard()).orElse(null);
            if (checkIdentitycard != null && checkIdentitycard.getId() != id) 
            	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_409_IDENTICARD), HttpStatus.CONFLICT);
            
           
    		for (String idA : ownDTO.getApartments()) {
    			Apartment apartment = apartmentRepository.findById(idA).orElse(null);
				if (apartment == null) 	
					return new ResponseEntity<>(new ResponseDTO(null, idA + " không tồn tại! "), HttpStatus.NOT_FOUND);	    
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
            return ResponseEntity.ok(new ResponseDTO(ownApartmentDTO, MessageSuccess.UPDATE_SUCCSESS));        
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    // < ------------------------------- Delete ----------------------------------->
    public ResponseEntity<ResponseDTO> deleteOwn(int id) {
        try {
        	 OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);
             if (ownApartment == null) 		
            	 return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_OWN_APARTMENT), HttpStatus.NOT_FOUND);

        	ownApmtRepository.deleteById(id);
        	fileHelper.deleteFile(ownApartment.getImage());
            return ResponseEntity.ok(new ResponseDTO(null, MessageSuccess.DELETE_SUCCSESS));  
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
    // < ------------------------------- Upload file ----------------------------------->
    public ResponseEntity<ResponseDTO>  uploadFile(MultipartFile mFile, int id){
    	if (mFile.isEmpty()) 
    		return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_400), HttpStatus.BAD_REQUEST);
    	
    	try {
    		OwnApartment ownApartment = ownApmtRepository.findById(id).orElse(null);
    		if (ownApartment == null) 
    			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
    		fileHelper.deleteFile(ownApartment.getImage());
			String fileName =  fileHelper.saveFile(mFile, "user" + id); 
			ownApartment.setImage(fileName);
			ownApartment = ownApmtRepository.save(ownApartment);	
			return ResponseEntity.ok(new ResponseDTO(null, MessageSuccess.UPLOAD_FILE_SUCCSESS));
		} catch (IOException e) {
			return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
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