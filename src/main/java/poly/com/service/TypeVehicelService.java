package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.TypeVehicel;
import poly.com.repository.TypeVehicelRepository;

@Service
public class TypeVehicelService {

	@Autowired
	TypeVehicelRepository typeVehicelRepository;
	
	public ResponseEntity<List<TypeVehicel>> findAll(){
		List<TypeVehicel> lists = typeVehicelRepository.findAll();
		return ResponseEntity.ok(lists);
	}
	
	// < ------------------------------- findById ----------------------------------->
	public ResponseEntity<TypeVehicel> findById(int id){
		try {
			TypeVehicel typeVehicel = typeVehicelRepository.findById(id).orElse(null);
			return ResponseEntity.ok(typeVehicel);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	// < ------------------------------- insert ----------------------------------->
	public ResponseEntity<TypeVehicel> createTypeVehicel(TypeVehicel newVehicel){
		try {
			if (typeVehicelRepository.existsByName(newVehicel.getName()))
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);	
			
		     newVehicel.setId(0);
		     newVehicel = typeVehicelRepository.save(newVehicel);
			return ResponseEntity.ok(newVehicel);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// < ------------------------------- update ----------------------------------->
	public ResponseEntity<TypeVehicel> updateTypeVehicel(int id, TypeVehicel newVehicel){
		try {		
			if(!typeVehicelRepository.existsById(id))
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);	
			
			TypeVehicel typeVehicel = typeVehicelRepository.findByName(newVehicel.getName()).orElse(null);	
			if (typeVehicel!= null && typeVehicel.getId() != id) 
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);	
			
			newVehicel.setId(id);
			newVehicel = typeVehicelRepository.save(newVehicel);
			return ResponseEntity.ok(newVehicel);		
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// < ------------------------------- Delete ----------------------------------->
		public ResponseEntity<String> deleteTypeVehicelr(int id) {
			try {
				TypeVehicel typeOld = typeVehicelRepository.findById(id).orElse(null);
				if (typeOld == null)
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
				
				typeVehicelRepository.deleteById(id);
				return ResponseEntity.ok("delete success");
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
}
