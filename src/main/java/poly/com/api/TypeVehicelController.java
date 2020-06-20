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
import org.springframework.web.bind.annotation.RestController;

import poly.com.constant.URL_API;
import poly.com.dto.ResponseDTO;
import poly.com.entity.TypeVehicel;
import poly.com.service.TypeVehicelService;

@RestController
@RequestMapping(URL_API.TYPE_VEHICEL)
public class TypeVehicelController {

	@Autowired
	TypeVehicelService typeVehicelService;
	
	@GetMapping()
	public ResponseEntity<ResponseDTO> findAll(){
		
		return typeVehicelService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> findById(@PathVariable int id){
		
		return typeVehicelService.findById(id);
	}
		
	@PostMapping()
	public ResponseEntity<ResponseDTO> createTypeVehicel(@Valid @RequestBody TypeVehicel typeVehicel){
		return typeVehicelService.createTypeVehicel(typeVehicel);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDTO> updateTypeVehicel(@PathVariable() int id,@Valid @RequestBody TypeVehicel typeVehicel){
		System.out.println(typeVehicel);
		return typeVehicelService.updateTypeVehicel(id, typeVehicel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> deleteTypeVehicel(@PathVariable int id){
		
		return typeVehicelService.deleteTypeVehicelr(id);
	}
	
}
