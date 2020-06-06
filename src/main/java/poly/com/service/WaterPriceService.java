package poly.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import poly.com.entity.PriceWater;
import poly.com.repository.PriceWaterRepository;

@Service
public class WaterPriceService {

// < ------------------------------------- Class Price Service ---------------------------------------- >

	@Autowired
	PriceWaterRepository priceWaterRepository;
// ---------------------------------------------------------

	// < --------------------------- find All -------------------------->
	public ResponseEntity<List<PriceWater>> findAll() {
		List<PriceWater> priceWater = priceWaterRepository.findAll();
		return ResponseEntity.ok(priceWater);
	}

	// < -------------------------- find by Id ---------------------------->
	public ResponseEntity<PriceWater> findById(int id) {
		try {
			PriceWater water = priceWaterRepository.findById(id).orElse(null);
			return ResponseEntity.ok(water);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < --------------------------- Create ---------------------------------->
	public ResponseEntity<PriceWater> createPriceWater(PriceWater priceWater) {
		try {
			priceWater.setId(0);
			PriceWater water = priceWaterRepository.save(priceWater);
			return ResponseEntity.ok(water);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------------ Update --------------------------------->
	public ResponseEntity<PriceWater> updatePriceWaterEntity(int id, PriceWater priceWater) {
		try {
			PriceWater water = priceWaterRepository.findById(id).orElse(null);
			if (water == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceWater.setId(id);
			water = priceWaterRepository.save(priceWater);
			return ResponseEntity.ok(water);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------------- Delete ----------------------------------->
	public ResponseEntity<String> deletePriceWater(int id) {
		try {
			PriceWater water = priceWaterRepository.findById(id).orElse(null);
			if (water == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceWaterRepository.deleteById(id);
			return ResponseEntity.ok("delete success");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}