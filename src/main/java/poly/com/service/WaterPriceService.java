
package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.PriceWater;
import poly.com.repository.PriceWaterRepository;

import java.util.List;

@Service
public class WaterPriceService {


    @Autowired
    PriceWaterRepository priceWaterRepository;
// ---------------------------------------------------------

    // < --------------------------- find All -------------------------->
    public ResponseEntity<List<PriceWater>> findAll() {
        List<PriceWater> priceWater = priceWaterRepository.findAll();
        return ResponseEntity.ok(priceWater);
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
        try { // kiểm tra tháng-năm đó đã có giá.
            PriceWater priceWaters = priceWaterRepository
                    .findByYearAndMonth(
                    		priceWater.getDate().getYear() + 1900,
							priceWater.getDate().getMonth() + 1);
            if (priceWaters != null)
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
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
            if (!priceWaterRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            PriceWater price = priceWaterRepository.findByYearAndMonth(
            		priceWater.getDate().getYear() + 1900,
                    priceWater.getDate().getMonth() + 1);
            if (id != price.getId())
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            priceWater.setId(id);
            PriceWater water = priceWaterRepository.save(priceWater);
            return ResponseEntity.ok(water);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------- Delete ----------------------------------->
    public ResponseEntity<String> deletePriceWater(int id) {
        try {
            if (!priceWaterRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            priceWaterRepository.deleteById(id);
            return ResponseEntity.ok("delete success");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}