package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.PriceElectricity;
import poly.com.repository.PriceElectricityRepository;

@Service
public class PriceElectricityService {
	@Autowired
	PriceElectricityRepository priceElectricityRepository;

	public ResponseEntity<List<PriceElectricity>> findPriceElectricityAll() {
		List<PriceElectricity> priceElectricities = priceElectricityRepository.findAll();

		return ResponseEntity.ok(priceElectricities);
	}

	
	
	public ResponseEntity<PriceElectricity> findPriceElectricitybyId(int id) {
		PriceElectricity priceElectricity = priceElectricityRepository.findById(id).orElse(null);
		if (priceElectricity == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(priceElectricity);
	}

	
	
	public ResponseEntity<PriceElectricity> createPriceElectricity(PriceElectricity priceElectricity) {
		PriceElectricity newPriceElectricity = null;
		priceElectricity.setId(0);
		try {
			newPriceElectricity = priceElectricityRepository.save(priceElectricity);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(newPriceElectricity);
	}

	
	
	public ResponseEntity<PriceElectricity> updatePriceElectricity(int id, PriceElectricity priceElectricity) {
		priceElectricity.setId(0);
		PriceElectricity pricebyid = priceElectricityRepository.findById(id).orElse(null);

		if (pricebyid == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		try {
			pricebyid = priceElectricityRepository.save(priceElectricity);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(pricebyid);

	}
	
	
	public ResponseEntity<String> deletePriceElectricity(int id){
		
		 PriceElectricity pricebyid = priceElectricityRepository.findById(id).orElse(null); 
		 
		   if (pricebyid == null) {
			   return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		   }
			
		   try {	   
			     priceElectricityRepository.deleteById(id);
			} catch (Exception e) {
				
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   
		 return ResponseEntity.ok("Deleted");
	}

}
