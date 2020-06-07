package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.PriceGarbage;
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

	// < -------------------------- find By Date --------------------->
	@SuppressWarnings("deprecation")
	public ResponseEntity<List<PriceManagement>> findDate(PriceManagement priceManagement) {
		int year = priceManagement.getDate().getYear() + 1900;
		int month = priceManagement.getDate().getMonth() + 1;
		List<PriceManagement> priceManagements = priceManagementRepository.findByYearAndMonth(year, month);
		return ResponseEntity.ok(priceManagements);
	}

	// < ------------------------------ Create --------------------------------->
	@SuppressWarnings("deprecation")
	public ResponseEntity<PriceManagement> createPriceManagement(PriceManagement priceManagement) {
		try {
			List<PriceManagement> priceManagements = priceManagementRepository.findByYearAndMonth(
					priceManagement.getDate().getYear() + 1900, priceManagement.getDate().getMonth() + 1);
			if (priceManagements.size() > 0)
				return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409
			priceManagement.setId(0);
			PriceManagement management = priceManagementRepository.save(priceManagement);
			return ResponseEntity.ok(management);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// <-------------------------------- Update --------------------------------->
	@SuppressWarnings("deprecation")
	public ResponseEntity<PriceManagement> updatePriceManagement(int id, PriceManagement priceManagement) {
		try {
			// id: priceWater không tồn tại
			PriceManagement management = priceManagementRepository.findById(id).orElse(null);
			if (management == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			// Giá các tháng trước đã có
			List<PriceManagement> priceManagements = priceManagementRepository.findByYearAndMonth(
					priceManagement.getDate().getYear() + 1900, priceManagement.getDate().getMonth() + 1);
			if (id != priceManagements.get(0).getId())
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			priceManagement.setId(id);
			PriceManagement priceManagementid = priceManagementRepository.findById(id).orElse(null);
			if (priceManagementid == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceManagementid = priceManagementRepository.save(priceManagement);
			return ResponseEntity.ok(priceManagementid);
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
