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

import poly.com.entity.PriceWater;
import poly.com.service.WaterPriceService;

@RestController
@RequestMapping("/api/price-water")
public class PriceWaterController {

	@Autowired
	WaterPriceService waterPriceService;
	
	@GetMapping()
	public ResponseEntity<List<PriceWater>> findAll()
	{
		return waterPriceService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PriceWater> findById(@PathVariable int  id)
	{
		return waterPriceService.findById(id);
	}
	
	@PostMapping()
	public ResponseEntity<PriceWater> createPriceWater(@RequestBody PriceWater priceWater)
	{
		return waterPriceService.createPriceWater(priceWater);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PriceWater> updatePriceWater(@PathVariable int id,@RequestBody PriceWater priceWater)
	{
		return waterPriceService.updatePriceWaterEntity(id, priceWater);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePriceWater(@PathVariable int id)
	{
		return waterPriceService.deletePriceWater(id);
	}
}
