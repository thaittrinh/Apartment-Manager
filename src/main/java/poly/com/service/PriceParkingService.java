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

	@Autowired
	PriceParkingRepository priceParkingRepository;
	
	public ResponseEntity<List<PriceParking>> findAll(){
		List<PriceParking> priceParkings = priceParkingRepository.findAll();
		
		return ResponseEntity.ok(priceParkings);
	}
	
	public ResponseEntity<PriceParking> findbyId(int id) {
		PriceParking priceParking = null;
		try {
		  priceParking = priceParkingRepository.findById(id).orElse(null);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(priceParking);
	}

	public ResponseEntity<PriceParking> createPriceParking(PriceParking priceParking) {
		PriceParking newPriceParking = null;
		priceParking.setId(0);
		try {
			newPriceParking = priceParkingRepository.save(priceParking);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(newPriceParking);
	}

	public ResponseEntity<PriceParking> updatePriceParking(int id, PriceParking priceParking) {
		PriceParking priceParkingOld = priceParkingRepository.findById(id).orElse(null);

		if (priceParkingOld == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		try {
			priceParking.setId(id);
			priceParkingOld = priceParkingRepository.save(priceParking);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(priceParkingOld);
	}

	public ResponseEntity<String> deletePriceManagemet(int id) {
		PriceParking priceParkingOld = priceParkingRepository.findById(id).orElse(null);

		if (priceParkingOld == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		try {
			priceParkingRepository.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("Delete success!");
	}
}
