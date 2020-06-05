package poly.com.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.com.entity.PriceParking;
import poly.com.security.service.PriceParkingService;

@RestController
@RequestMapping("/api/price_parking")
public class PriceParkingController {

	@Autowired
	PriceParkingService priceParkingService;

	@GetMapping
	public ResponseEntity<List<PriceParking>> findAll() {
		return priceParkingService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PriceParking> findbyId(@PathVariable int id) {
		return priceParkingService.findbyId(id);
	}

	@PostMapping
	public ResponseEntity<PriceParking> createPriceParking(@RequestBody PriceParking priceParking) {
		return priceParkingService.createPriceParking(priceParking);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PriceParking> updatePriceParking(@PathVariable int id,@RequestBody PriceParking priceParking) {
		return priceParkingService.updatePriceParking(id, priceParking);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePriceParking(@PathVariable int id) {
		return priceParkingService.deletePriceManagemet(id);
	}

}
