package poly.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.com.constant.URL_API;
import poly.com.dto.ResponseDTO;
import poly.com.entity.PriceElectricity;
import poly.com.request.CreateIndexRequest;
import poly.com.service.ApartmentIndexService;

@RestController
@RequestMapping(URL_API.APARTMENT_INDEX)
public class ApartmentIndexController {

	@Autowired
	ApartmentIndexService apartmentIndexService;
	
	@PostMapping("/test")
	public double test(@RequestBody PriceElectricity priceWater){
		
	
		System.out.println(priceWater.getDate());
		
		return  apartmentIndexService.test(priceWater.getDate());
	}
	
	
	
	@GetMapping
	public ResponseEntity<ResponseDTO> findAll(){
		
		return  apartmentIndexService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO> create(@RequestBody CreateIndexRequest request){
		
		return  apartmentIndexService.create(request );
	}
	
}
