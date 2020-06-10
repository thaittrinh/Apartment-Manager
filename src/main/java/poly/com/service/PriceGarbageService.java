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

	// < -------------------- find by Id ---------------------------->
	public ResponseEntity<PriceGarbage> findPriceGarbageById(int id) {
		try {
			PriceGarbage priceGarbage = priceGarbageRepository.findById(id).orElse(null);
			if (priceGarbage == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return ResponseEntity.ok(priceGarbage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ---------------------------- Create ------------------------->
	@SuppressWarnings("deprecation")
	public ResponseEntity<PriceGarbage> createPriceGarbage(PriceGarbage priceGarbage) {
		try {
			List<PriceGarbage> priceGarbages = priceGarbageRepository
					.findByYearAndMonth(priceGarbage.getDate().getYear() + 1900, priceGarbage.getDate().getMonth() + 1);
			if (priceGarbages.size() > 0)
				return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409
			priceGarbage.setId(0);
			PriceGarbage garbage = priceGarbageRepository.save(priceGarbage);
			return ResponseEntity.ok(garbage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ----------------------------- update ------------------------- >
	@SuppressWarnings("deprecation")
	public ResponseEntity<PriceGarbage> updatePriceGarbage(int id, PriceGarbage priceGarbage) {
		try {
			// id: priceWater không tồn tại
			PriceGarbage grabage = priceGarbageRepository.findById(id).orElse(null);
			if (grabage == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			// Giá các tháng trước đã có
			List<PriceGarbage> priceGarbages = priceGarbageRepository
					.findByYearAndMonth(priceGarbage.getDate().getYear() + 1900, priceGarbage.getDate().getMonth() + 1);
			if (id != priceGarbages.get(0).getId())
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			priceGarbage.setId(id);
			PriceGarbage priceGarbageid = priceGarbageRepository.findById(id).orElse(null);
			if (priceGarbageid == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceGarbageid = priceGarbageRepository.save(priceGarbage);
			return ResponseEntity.ok(priceGarbageid);
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
			return ResponseEntity.ok("Delete success");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
