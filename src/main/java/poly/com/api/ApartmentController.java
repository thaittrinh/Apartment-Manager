package poly.com.api;

import java.util.List;

import javax.validation.Valid;

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

import poly.com.entity.Apartment;
import poly.com.service.ApartmentService;

@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {

	@Autowired
	ApartmentService apartmentService;
	
	// <------------------------- findAll --------------------------->
	@GetMapping
	public ResponseEntity<List<Apartment>> findAll() {
		
		return apartmentService.findAll();
	}

	// < ----------------------- findById --------------------------->
	@GetMapping("/{id}")
	public ResponseEntity<Apartment> findbyId(@PathVariable String id) {
		return apartmentService.findById(id);
	}

	// < ------------------------ Create ----------------------------->
	@PostMapping
	public ResponseEntity<Apartment> createPriceManagement( @Valid @RequestBody Apartment apartment) {
		return apartmentService.createApartment(apartment);
	}

	// < -------------------------- Update ---------------------------->
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePriceManagement(@PathVariable String id,
																 @Valid @RequestBody Apartment apartment) {
		return apartmentService.updateApartment(id, apartment);
	}

	// < -------------------------- Delete --------------------------->
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePricemanagement(@PathVariable String id) {
		return apartmentService.deleteApartment(id);
	
	}
}
