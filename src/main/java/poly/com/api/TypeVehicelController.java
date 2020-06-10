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

import poly.com.entity.TypeVehicel;
import poly.com.service.TypeVehicelService;

@RestController
@RequestMapping("/api/type-vehicel")
public class TypeVehicelController {

	@Autowired
	TypeVehicelService typeVehicelService;
	
	@GetMapping()
	public ResponseEntity<List<TypeVehicel>> findAll(){
		
		return typeVehicelService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TypeVehicel> findById(@PathVariable int id){
		
		return typeVehicelService.findById(id);
	}
		
	@PostMapping()
	public ResponseEntity<TypeVehicel> createTypeVehicel(@RequestBody TypeVehicel typeVehicel){
		
		return typeVehicelService.createTypeVehicel(typeVehicel);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TypeVehicel> updateTypeVehicel(@PathVariable() int id, @RequestBody TypeVehicel typeVehicel){
		
		return typeVehicelService.updateTypeVehicel(id, typeVehicel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTypeVehicel(@PathVariable int id){
		
		return typeVehicelService.deleteTypeVehicelr(id);
	}
	
}
