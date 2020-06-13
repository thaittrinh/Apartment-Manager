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

import poly.com.entity.OwnApartment;
import poly.com.service.OwnApartmentService;

@RestController
@RequestMapping("/api/own-apartment")
public class OwnApartmentController {

	@Autowired
	OwnApartmentService ownApartmentService;
// ------------------------------------------------

	// <------------------------- findAll --------------------------->
	@GetMapping()
	public ResponseEntity<List<OwnApartment>> findAll() {
		return ownApartmentService.findAll();
	}

	// < ----------------------- findById --------------------------->
	@GetMapping("/{id}")
	public ResponseEntity<OwnApartment> findById(@PathVariable int id) {
		return ownApartmentService.findById(id);
	}

	// < ------------------------ Create ----------------------------->
	@PostMapping()
	public ResponseEntity<OwnApartment> createOwn(@Valid @RequestBody OwnApartment ownApartment) {
		return ownApartmentService.createOwn(ownApartment);
	}

	// < -------------------------- Update ---------------------------->
	@PutMapping("/{id}")
	public ResponseEntity<OwnApartment> updateOwn(@PathVariable int id,
												  @Valid @RequestBody OwnApartment ownApartment) {
		return ownApartmentService.updateOwn(id, ownApartment);
	}

	// < -------------------------- Delete --------------------------->
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOwn(@PathVariable int id) {
		return ownApartmentService.deleteOwn(id);
	}

}