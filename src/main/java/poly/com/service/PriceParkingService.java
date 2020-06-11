package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.PriceParking;
import poly.com.repository.PriceParkingRepository;

import java.util.List;

@Service
public class PriceParkingService {

// < ------------------------------- class Price Parking Service ----------------------------------> 

    @Autowired
    PriceParkingRepository priceParkingRepository;
// --------------------------------------------------------

    // < ------------------------------ find all ------------------------->
    public ResponseEntity<List<PriceParking>> findAll() {
        List<PriceParking> priceParkings = priceParkingRepository.findAll();
        return ResponseEntity.ok(priceParkings);
    }

    // <-------------------------------- find by Id ------------------------>
    public ResponseEntity<PriceParking> findbyId(int id) {
        try {
            PriceParking priceParking = priceParkingRepository.findById(id).orElse(null);
            if (priceParking == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return ResponseEntity.ok(priceParking);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   


	// < -------------------------------- Create ---------------------------------->
	@SuppressWarnings("deprecation")
	public ResponseEntity<PriceParking> createPriceParking(PriceParking priceParking) {	
		try {
			PriceParking price =  priceParkingRepository.findByYearMonthAndLimit(
				                                                priceParking.getDate().getYear() + 1900,
				                                                priceParking.getDate().getMonth() + 1,
				                                                priceParking.getTypeVehicel()); 	
			if (price != null) 
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
			
			priceParking.setId(0);
			priceParking = priceParkingRepository.save(priceParking);
			return ResponseEntity.ok(priceParking);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
    // < ----------------------------------- Update -------------------------------- >
	@SuppressWarnings("deprecation")
	public ResponseEntity<PriceParking> updatePriceParking(int id, PriceParking priceParking) {
		
		try {
			if (!priceParkingRepository.existsById(id)) 
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			
			PriceParking price =  priceParkingRepository.findByYearMonthAndLimit(
										                    priceParking.getDate().getYear() + 1900,
										                    priceParking.getDate().getMonth() + 1,
										                    priceParking.getTypeVehicel()); 
			if (price!= null && price.getId() != id) 
				return new ResponseEntity<>(null, HttpStatus.CONFLICT);
				
			priceParking.setId(id);
			priceParking= priceParkingRepository.save(priceParking);
			return ResponseEntity.ok(priceParking);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

    // < --------------------------------- Delete -----------------------------------> 
    public ResponseEntity<String> deletePriceManagemet(int id) {
        try {
            if (!priceParkingRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            priceParkingRepository.deleteById(id);
            return ResponseEntity.ok("Delete success!");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
