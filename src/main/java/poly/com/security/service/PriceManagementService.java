package poly.com.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.PriceManagement;
import poly.com.repository.PriceManagementRepository;

@Service
public class PriceManagementService {

	@Autowired
	PriceManagementRepository priceManagementRepository;

	public ResponseEntity<List<PriceManagement>> findAll() {
		List<PriceManagement> priceManagements = priceManagementRepository.findAll();

		return ResponseEntity.ok(priceManagements);
	}

	public ResponseEntity<PriceManagement> findbyId(int id) {
		PriceManagement priceManagement = priceManagementRepository.findById(id).orElse(null);
		if (priceManagement == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(priceManagement);
	}

	public ResponseEntity<PriceManagement> createPriceManagement(PriceManagement priceManagement) {
		PriceManagement newPriceManagement = null;
		priceManagement.setId(0);
		try {
			newPriceManagement = priceManagementRepository.save(priceManagement);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(newPriceManagement);
	}

	public ResponseEntity<PriceManagement> updatePriceManagement(int id, PriceManagement priceManagement) {
		PriceManagement priceManagementOld = priceManagementRepository.findById(id).orElse(null);

		if (priceManagementOld == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		try {
			priceManagement.setId(id);
			priceManagementOld = priceManagementRepository.save(priceManagement);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(priceManagementOld);
	}

	public ResponseEntity<String> deletePriceManagemet(int id) {
		PriceManagement priceManagementOld = priceManagementRepository.findById(id).orElse(null);

		if (priceManagementOld == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		try {
			priceManagementRepository.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("Delete success!");
	}
}
