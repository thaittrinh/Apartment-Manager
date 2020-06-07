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

// < ------------------------------- Class PriceElectricity Service ----------------------------->

	@Autowired
	PriceElectricityRepository priceElectricityRepository;
// ----------------------------------------------------------------------

	// < --------------------------- Find All --------------------------->
	public ResponseEntity<List<PriceElectricity>> findAllElectricity() {
		List<PriceElectricity> priceElectricities = priceElectricityRepository.findAll();
		return ResponseEntity.ok(priceElectricities);
	}

    // < --------------------------- Find By Id -------------------------->
	public ResponseEntity<PriceElectricity> findPriceElectricitybyId(int id) {
		try {
			PriceElectricity priceElectricity = priceElectricityRepository.findById(id).orElse(null);
			if(priceElectricity	== null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return ResponseEntity.ok(priceElectricity);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 *  insert vs update bắt lỗi cùng ngày tạo nhưng có chung định mức ( chưa bắt)
	 */
	
    // < ---------------------------- Create ------------------------------------>
	public ResponseEntity<PriceElectricity> createPriceElectricity(PriceElectricity priceElectricity) {
		try {
			priceElectricity.setId(0);
			PriceElectricity newPriceElectricity = priceElectricityRepository.save(priceElectricity);
			return ResponseEntity.ok(newPriceElectricity);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    // < ----------------------------- Update ------------------------------------->
	public ResponseEntity<PriceElectricity> updatePriceElectricity(int id, PriceElectricity priceElectricity) {
		try {
			PriceElectricity pricebyid = priceElectricityRepository.findById(id).orElse(null);
			if (pricebyid == null) 
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			pricebyid = priceElectricityRepository.save(priceElectricity);
			return ResponseEntity.ok(pricebyid);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    // < --------------------------- Delete ------------------------------------------->
	public ResponseEntity<String> deletePriceElectricity(int id) {
		try {
			PriceElectricity pricebyid = priceElectricityRepository.findById(id).orElse(null);
			if (pricebyid == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceElectricityRepository.deleteById(id);
			return ResponseEntity.ok("Deleted");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
