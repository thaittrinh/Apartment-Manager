package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.PriceManagement;
import poly.com.repository.PriceManagementRepository;

@Service
public class PriceManagementService {
	
// < ----------------------------- Class Price Management Service ------------------------->

	@Autowired
	PriceManagementRepository priceManagementRepository;
// < ---------------------------------------------------

	// < ---------------------------- find All ------------------------------->
	public ResponseEntity<List<PriceManagement>> findAll() {
		List<PriceManagement> priceManagements = priceManagementRepository.findAll();
		return ResponseEntity.ok(priceManagements);
	}

	// < ----------------------------- find by Id ------------------------------ >
	public ResponseEntity<PriceManagement> findbyId(int id) {
		try {
			PriceManagement priceManagement = priceManagementRepository.findById(id).orElse(null);
			return ResponseEntity.ok(priceManagement);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------------ Create --------------------------------->
	public ResponseEntity<PriceManagement> createPriceManagement(PriceManagement priceManagement) {
		try {
			priceManagement.setId(0);
			PriceManagement newPriceManagement = priceManagementRepository.save(priceManagement);
			return ResponseEntity.ok(newPriceManagement);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// <-------------------------------- Update --------------------------------->
	public ResponseEntity<PriceManagement> updatePriceManagement(int id, PriceManagement priceManagement) {
		try {
			PriceManagement priceManagementOld = priceManagementRepository.findById(id).orElse(null);
			if (priceManagementOld == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceManagement.setId(id);
			priceManagementOld = priceManagementRepository.save(priceManagement);
			return ResponseEntity.ok(priceManagementOld);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------------- Delete ----------------------------------->
	public ResponseEntity<String> deletePriceManagemet(int id) {
		try {
			PriceManagement priceManagementOld = priceManagementRepository.findById(id).orElse(null);
			if (priceManagementOld == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceManagementRepository.deleteById(id);
			return ResponseEntity.ok("Delete success!");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
