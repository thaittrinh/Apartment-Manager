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
	@Autowired
	PriceGarbageRepository priceGarbageRepository;

	public ResponseEntity<List<PriceGarbage>> findPriceGarbageAll() {
		List<PriceGarbage> priceGarbages = priceGarbageRepository.findAll();

		return ResponseEntity.ok(priceGarbages);
	}

	public ResponseEntity<PriceGarbage> findPriceGarbageById(int id) {
		PriceGarbage priceGarbage = priceGarbageRepository.findById(id).orElse(null);
		if (priceGarbage == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(priceGarbage);
	}

	public ResponseEntity<PriceGarbage> createPriceGarbage(PriceGarbage priceGarbage) {
		PriceGarbage newPriceGarbage = null;
		priceGarbage.setId(0);
		try {
			newPriceGarbage = priceGarbageRepository.save(priceGarbage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(newPriceGarbage);
	}

	public ResponseEntity<PriceGarbage> updatePriceGarbage(int id, PriceGarbage priceGarbage) {
		priceGarbage.setId(0);
		PriceGarbage pricebyid = priceGarbageRepository.findById(id).orElse(null);

		if (pricebyid == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		try {
			pricebyid = priceGarbageRepository.save(priceGarbage);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(pricebyid);

	}

	public ResponseEntity<String> deletePriceGarbage(int id) {

		PriceGarbage pricebyid = priceGarbageRepository.findById(id).orElse(null);

		if (pricebyid == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		try {
			priceGarbageRepository.deleteById(id);
		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok("Deleted");
	}

}
