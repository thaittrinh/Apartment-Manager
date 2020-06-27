package poly.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import poly.com.dto.ResponseDTO;
import poly.com.request.CreateIndexRequest;
import poly.com.service.ApartmentIndexService;

@RestController
@RequestMapping("/api/apartment-index")
public class ApartmentIndexController {

	@Autowired
	ApartmentIndexService apartmentIndexService;

	@GetMapping
	public ResponseEntity<ResponseDTO> findAll(){
		
		return  apartmentIndexService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> findAll(@PathVariable int id){
		
		return  apartmentIndexService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO> create(@RequestBody CreateIndexRequest request){
		
		return  apartmentIndexService.create(request );
	}
	
}
