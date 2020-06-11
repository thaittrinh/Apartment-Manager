package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.PriceElectricity;
import poly.com.entity.PriceWater;
import poly.com.repository.PriceElectricityRepository;

import java.util.List;

@Service
public class PriceElectricityService {

// < ------------------------------- Class PriceElectricity Service ----------------------------->

    @Autowired
    PriceElectricityRepository priceElectricityRepository;
// ----------------------------------------------------------------------

    // < --------------------------- Find All --------------------------->
    public ResponseEntity<List<PriceElectricity>> findAllElectricity() {
        List<PriceElectricity> priceElectricities = priceElectricityRepository.findAll();
        return ResponseEntity.ok(priceElectricities);
    }

    // < --------------------------- Find By Id -------------------------->
    public ResponseEntity<PriceElectricity> findPriceElectricitybyId(int id) {
        try {
            PriceElectricity priceElectricity = priceElectricityRepository.findById(id).orElse(null);
            if (priceElectricity == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return ResponseEntity.ok(priceElectricity);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ---------------------------- Create ------------------------------------>
    public ResponseEntity<PriceElectricity> createPriceElectricity(PriceElectricity priceElectricity) {
        try {
            PriceElectricity priceElectricities = priceElectricityRepository.findByLimit(
                    priceElectricity.getDate().getYear()+ 1900,
                    priceElectricity.getDate().getMonth() + 1,
                    priceElectricity.getLimits());
            if (priceElectricities != null)
                return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409
            priceElectricity.setId(0);
            PriceElectricity electricity = priceElectricityRepository.save(priceElectricity);
            return ResponseEntity.ok(electricity);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ----------------------------- Update ------------------------------------->
    public ResponseEntity<PriceElectricity> updatePriceElectricity(int id, PriceElectricity priceElectricity) {
        try {
            if (!priceElectricityRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            PriceElectricity electricityid = priceElectricityRepository.findByLimit(
                    priceElectricity.getDate().getYear() + 1900,
                    priceElectricity.getDate().getMonth() + 1,
                    priceElectricity.getLimits());
            if(electricityid.getId() != id)
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            priceElectricity.setId(id);
            priceElectricity = priceElectricityRepository.save(priceElectricity);
            return ResponseEntity.ok(priceElectricity);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < --------------------------- Delete ------------------------------------------->
    public ResponseEntity<String> deletePriceElectricity(int id) {
        try {
            if (!priceElectricityRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            priceElectricityRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
