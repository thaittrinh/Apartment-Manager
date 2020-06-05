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

import poly.com.entity.PriceElectricity;
import poly.com.service.PriceElectricityService;

@RestController
@RequestMapping("/api/priceelectricity")
public class PriceElectricityController {

	@Autowired
	PriceElectricityService priceElectricityService;

	@GetMapping()
	public ResponseEntity<List<PriceElectricity>> findPriceElectricityAll() {

		return priceElectricityService.findPriceElectricityAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PriceElectricity> findPriceElectricitybyId(@PathVariable int id) {
		return priceElectricityService.findPriceElectricitybyId(id);
	}

	@PostMapping()
	public ResponseEntity<PriceElectricity> createPriceElectricity(@RequestBody PriceElectricity priceElectricity) {

		return priceElectricityService.createPriceElectricity(priceElectricity);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PriceElectricity> updatePriceElectricity(@PathVariable int id, @RequestBody PriceElectricity priceElectricity) {

		return priceElectricityService.updatePriceElectricity(id, priceElectricity);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePriceElectricity(@PathVariable int id) {

		return priceElectricityService.deletePriceElectricity(id);
	}

}
