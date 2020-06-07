package poly.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.PriceParking;
import poly.com.repository.PriceParkingRepository;

@Service
public class PriceParkingService {

// < ------------------------------- class Price Parking Service ----------------------------------> 

	@Autowired
	PriceParkingRepository priceParkingRepository;
// --------------------------------------------------------
	
	// < ------------------------------ find all ------------------------->
	public ResponseEntity<List<PriceParking>> findAll() {
		List<PriceParking> priceParkings = priceParkingRepository.findAll();
		return ResponseEntity.ok(priceParkings);
	}

	// <-------------------------------- find by Id ------------------------>
	public ResponseEntity<PriceParking> findbyId(int id) {
		try {
			PriceParking priceParking = priceParkingRepository.findById(id).orElse(null);
			if(priceParking == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return ResponseEntity.ok(priceParking);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < -------------------------------- Create ---------------------------------->
	public ResponseEntity<PriceParking> createPriceParking(PriceParking priceParking) {
		try {
			priceParking.setId(0);
			PriceParking newPriceParking = priceParkingRepository.save(priceParking);
			return ResponseEntity.ok(newPriceParking);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    // < ----------------------------------- Update -------------------------------- >
	public ResponseEntity<PriceParking> updatePriceParking(int id, PriceParking priceParking) {
		try {
			PriceParking priceParkingOld = priceParkingRepository.findById(id).orElse(null);
			if (priceParkingOld == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceParking.setId(id);
			priceParkingOld = priceParkingRepository.save(priceParking);
			return ResponseEntity.ok(priceParkingOld);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
    // < --------------------------------- Delete -----------------------------------> 
	public ResponseEntity<String> deletePriceManagemet(int id) {
		try {
			PriceParking priceParkingOld = priceParkingRepository.findById(id).orElse(null);
			if (priceParkingOld == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceParkingRepository.deleteById(id);
			return ResponseEntity.ok("Delete success!");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
