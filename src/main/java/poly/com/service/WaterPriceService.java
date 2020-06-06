package poly.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import poly.com.entity.PriceWater;
import poly.com.repository.PriceWaterRepository;

@Service
public class WaterPriceService {

	@Autowired
	PriceWaterRepository priceWaterRepository;
	

	
	public ResponseEntity<List<PriceWater>> findAll()
	{
		List<PriceWater> priceWater = priceWaterRepository.findAll();
		return ResponseEntity.ok(priceWater);
	}
	
	public ResponseEntity<PriceWater> findById(int id)
	{
		PriceWater water = null;
		try {
		   water =    priceWaterRepository.findById(id).orElse(null);
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		return ResponseEntity.ok(water);
	}
	
	public ResponseEntity<PriceWater> createPriceWater(PriceWater priceWater)
	{
		PriceWater water = null;
		priceWater.setId(0);
		try {
			water = priceWaterRepository.save(priceWater);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(water);
	}
	
	public ResponseEntity<PriceWater> updatePriceWaterEntity (int id, PriceWater priceWater)
	{
		PriceWater water= priceWaterRepository.findById(id).orElse(null);
		if(water==null) // giá điện này ko tồn tại để update  
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
		try {
			priceWater.setId(id);
			water = priceWaterRepository.save(priceWater);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok(water);
	}
	public ResponseEntity<String> deletePriceWater(int id)
	{
		PriceWater water = priceWaterRepository.findById(id).orElse(null);
		if(water == null)
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		try {
			priceWaterRepository.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return ResponseEntity.ok("Xóa Thành Công");
	}
}