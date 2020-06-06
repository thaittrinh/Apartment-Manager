package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.PriceGarbage;

import poly.com.repository.PriceGarbageRepository;

@Service
public class PriceGarbageService {

// < ------------------------------ Class Price GarbageServices -------------------------------> 

	@Autowired
	PriceGarbageRepository priceGarbageRepository;
// -------------------------------------------------

	// < -------------------- find All ---------------------------->
	public ResponseEntity<List<PriceGarbage>> findPriceGarbageAll() {
		List<PriceGarbage> priceGarbages = priceGarbageRepository.findAll();
		return ResponseEntity.ok(priceGarbages);
	}

	/**
	 * insert update bắt trùng ngày (xem trong entity) - status code 409
	 * / quản lý giá theo ngày-tháng-năm
	 * 1 ngày ko thể có 2 giá
	 * insert nếu trùng ngày thì bắt update lại giá ngày đó ko thì thôi
	 * update kiểm tra ngoài id đó còn id nào có ngày đó ko nếu có thì ko cho cập nhật
	 *
	 */
	
	// < -------------------- find by Id ---------------------------->
	public ResponseEntity<PriceGarbage> findPriceGarbageById(int id) {
		try {
			PriceGarbage priceGarbage = priceGarbageRepository.findById(id).orElse(null);
			return ResponseEntity.ok(priceGarbage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    // < ---------------------------- Create ------------------------->
	public ResponseEntity<PriceGarbage> createPriceGarbage(PriceGarbage priceGarbage) {
		try {
			priceGarbage.setId(0);
			PriceGarbage newPriceGarbage = priceGarbageRepository.save(priceGarbage);
			return ResponseEntity.ok(newPriceGarbage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    // < ----------------------------- update ------------------------- > 
	public ResponseEntity<PriceGarbage> updatePriceGarbage(int id, PriceGarbage priceGarbage) {
		try {
			priceGarbage.setId(0);
			PriceGarbage pricebyid = priceGarbageRepository.findById(id).orElse(null);
			if (pricebyid == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			pricebyid = priceGarbageRepository.save(priceGarbage);
			return ResponseEntity.ok(pricebyid);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    // < ----------------------------- Delete -------------------------->
	public ResponseEntity<String> deletePriceGarbage(int id) {
		try {
			PriceGarbage pricebyid = priceGarbageRepository.findById(id).orElse(null);
			if (pricebyid == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceGarbageRepository.deleteById(id);
			return ResponseEntity.ok("Deleted");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
