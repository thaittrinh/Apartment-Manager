package poly.com.service;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.entity.PriceWater;
import poly.com.repository.PriceWaterRepository;

/**
 * Giá chỉ đổi 1 lần trong tháng
 * Chỉ có thể cập nhật lại giá đó chứ ko thêm được giá thứ 2 cùng tháng - năm
 */
@Service
public class WaterPriceService {

// < ------------------------------------- Class Price Service ---------------------------------------- >

	@Autowired
	PriceWaterRepository priceWaterRepository;
// ---------------------------------------------------------

	// < --------------------------- find All -------------------------->
	public ResponseEntity<List<PriceWater>> findAll() {
		List<PriceWater> priceWater = priceWaterRepository.findAll();
		return ResponseEntity.ok(priceWater);
	}

	public ResponseEntity<List<PriceWater>> findDate(PriceWater priceWater) {
		int year = priceWater.getDate().getYear() + 1900;
	    int month = priceWater.getDate().getMonth() + 1;
	
	   List<PriceWater> priceWaters = priceWaterRepository.findByYearAndMonth(year, month);
		
		return ResponseEntity.ok(priceWaters);
	}
	
	// < -------------------------- find by Id ---------------------------->
	public ResponseEntity<PriceWater> findById(int id) {
		try {
			PriceWater water = priceWaterRepository.findById(id).orElse(null);
			return ResponseEntity.ok(water);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < --------------------------- Create ---------------------------------->
	@SuppressWarnings("deprecation")
	public ResponseEntity<PriceWater> createPriceWater(PriceWater priceWater) {
		try {  // kiểm tra tháng-năm đó đã có giá. 
			List<PriceWater> priceWaters = priceWaterRepository.findByYearAndMonth(priceWater.getDate().getYear() + 1900,
	 		                                                                       priceWater.getDate().getMonth() + 1);
			if (priceWaters.size() > 0) 		
				return  new ResponseEntity<>(null, HttpStatus.CONFLICT);		
			priceWater.setId(0);
			PriceWater water = priceWaterRepository.save(priceWater);			
			return ResponseEntity.ok(water);
		} catch (Exception e) {			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------------ Update --------------------------------->
	@SuppressWarnings("deprecation")
	public ResponseEntity<PriceWater> updatePriceWaterEntity(int id, PriceWater priceWater) {
		try {	
			// id: priceWater không tồn tại
			PriceWater water = priceWaterRepository.findById(id).orElse(null);
			if (water == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			// Giá các tháng trước đã có
			List<PriceWater> priceWaters = priceWaterRepository.findByYearAndMonth(priceWater.getDate().getYear() + 1900,
                                                                                   priceWater.getDate().getMonth() + 1);			
			for (PriceWater p : priceWaters) { 
				int year = p.getDate().getYear();
				int month = p.getDate().getMonth();
				if (year == priceWater.getDate().getYear() && month == priceWater.getDate().getMonth()) 
					return new ResponseEntity<>(null, HttpStatus.CONFLICT);			
			}
			priceWater.setId(id);
			water = priceWaterRepository.save(priceWater);
			return ResponseEntity.ok(water);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// < ------------------------------- Delete ----------------------------------->
	public ResponseEntity<String> deletePriceWater(int id) {
		try {
			PriceWater water = priceWaterRepository.findById(id).orElse(null);
			if (water == null)
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			priceWaterRepository.deleteById(id);
			return ResponseEntity.ok("delete success");
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}