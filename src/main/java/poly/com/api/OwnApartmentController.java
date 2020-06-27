package poly.com.api;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poly.com.dto.OwnApartmentDTO;
import poly.com.dto.ResponseDTO;
import poly.com.service.OwnApartmentService;

@RestController
@RequestMapping("/api/own-apartment")
public class OwnApartmentController {

	@Autowired
	OwnApartmentService ownApartmentService;
// ------------------------------------------------

	// <------------------------- findAll --------------------------->

	@GetMapping()
	public ResponseEntity<ResponseDTO> findAll() {
		return ownApartmentService.findAll();
	}
	

	// < ----------------------- findById --------------------------->
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> findById(@PathVariable int id) {
		return ownApartmentService.findById(id);
	}

	// < ------------------------ Create ----------------------------->
   
	@PostMapping()
	public ResponseEntity<ResponseDTO> createOwn(@Valid @RequestBody OwnApartmentDTO ownDTO ){
		
		return ownApartmentService.createOwn(ownDTO);
	}

	@PostMapping("/upload-file/{id}")
	public ResponseEntity<ResponseDTO> uploadFile(@PathVariable int id , @RequestParam("file") MultipartFile mFile) {
	  return ownApartmentService.uploadFile(mFile, id);
	}
	
	// < -------------------------- Update ---------------------------->
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateOwn(@PathVariable int id, @Valid @RequestBody OwnApartmentDTO ownDTO) {
		return ownApartmentService.updateOwn(id, ownDTO);
	}

	// < -------------------------- Delete --------------------------->
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteOwn(@PathVariable int id) {
		return ownApartmentService.deleteOwn(id);
	}

}