package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.constant.MessageError;
import poly.com.constant.MessageSuccess;
import poly.com.dto.ResponseDTO;
import poly.com.entity.TypeVehicel;
import poly.com.repository.TypeVehicelRepository;

@Service
public class TypeVehicelService {

	@Autowired
	TypeVehicelRepository typeVehicelRepository;
	
	public ResponseEntity<ResponseDTO> findAll(){
		List<TypeVehicel> lists = typeVehicelRepository.findAll();
		return ResponseEntity.ok(new ResponseDTO(lists, null));
	}
	
	// < ------------------------------- findById ----------------------------------->
	public ResponseEntity<ResponseDTO> findById(int id){
		try {
			TypeVehicel typeVehicel = typeVehicelRepository.findById(id).orElse(null);
			return ResponseEntity.ok(new ResponseDTO(typeVehicel, null));
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	// < ------------------------------- insert ----------------------------------->
	public ResponseEntity<ResponseDTO> createTypeVehicel(TypeVehicel newVehicel){
		try {
			if (typeVehicelRepository.existsByName(newVehicel.getName()))
				return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_409_TYPE_VEHICEL), HttpStatus.CONFLICT);	
			
		     newVehicel.setId(0);
		     newVehicel = typeVehicelRepository.save(newVehicel);
			return ResponseEntity.ok(new ResponseDTO(newVehicel, MessageSuccess.INSERT_SUCCSESS));		
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// < ------------------------------- update ----------------------------------->
	public ResponseEntity<ResponseDTO> updateTypeVehicel(int id, TypeVehicel newVehicel){
		try {		
			if(!typeVehicelRepository.existsById(id))
				return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_TYPE_VEHICEL), HttpStatus.NOT_FOUND);	
			
			TypeVehicel typeVehicel = typeVehicelRepository.findByName(newVehicel.getName()).orElse(null);	
			if (typeVehicel!= null && typeVehicel.getId() != id) 
				return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_409_TYPE_VEHICEL), HttpStatus.CONFLICT);
			
			newVehicel.setId(id);
			newVehicel = typeVehicelRepository.save(newVehicel);
			return ResponseEntity.ok(new ResponseDTO(newVehicel, MessageSuccess.UPDATE_SUCCSESS));	
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// < ------------------------------- Delete ----------------------------------->
		public ResponseEntity<ResponseDTO> deleteTypeVehicelr(int id) {
			try {
				TypeVehicel typeOld = typeVehicelRepository.findById(id).orElse(null);
				if (typeOld == null)
					return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_TYPE_VEHICEL), HttpStatus.NOT_FOUND);	
				
				typeVehicelRepository.deleteById(id);
				return ResponseEntity.ok(new ResponseDTO(null, MessageSuccess.DELETE_SUCCSESS));
			} catch (Exception e) {
				return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
}
