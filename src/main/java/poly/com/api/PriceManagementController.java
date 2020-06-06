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

import poly.com.entity.PriceManagement;
import poly.com.service.PriceManagementService;

@RestController
@RequestMapping("/api/price_management")
public class PriceManagementController {

	@Autowired
	PriceManagementService priceManagementService;

	@GetMapping
	public ResponseEntity<List<PriceManagement>> findAll() {
		return priceManagementService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PriceManagement> findbyId(@PathVariable int id) {
		return priceManagementService.findbyId(id);
	}

	@PostMapping
	public ResponseEntity<PriceManagement> createPriceManagement(@RequestBody PriceManagement priceManagement) {
		return priceManagementService.createPriceManagement(priceManagement);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PriceManagement> updatePriceManagement(@PathVariable int id, @RequestBody PriceManagement priceManagement){
		return priceManagementService.updatePriceManagement(id, priceManagement);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePricemanagement(@PathVariable int id){
		return priceManagementService.deletePriceManagemet(id);
	}
}
