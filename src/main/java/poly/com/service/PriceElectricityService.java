package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.PriceElectricity;
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
            List<PriceElectricity> priceElectricities = priceElectricityRepository
                    .findByLimit(priceElectricity.getLimits());
            if (priceElectricities.size() > 0)
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
            PriceElectricity electricity = priceElectricityRepository.findById(id).orElse(null);
            if (electricity == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            List<PriceElectricity> priceElectricitys = priceElectricityRepository
                    .findByLimit(priceElectricity.getLimits());
            if (id != priceElectricitys.get(0).getId())
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            priceElectricity.setId(id);
            PriceElectricity priceElectricityid = priceElectricityRepository.findById(id).orElse(null);
            if (priceElectricityid == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            priceElectricityid = priceElectricityRepository.save(priceElectricity);
            return ResponseEntity.ok(priceElectricityid);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < --------------------------- Delete ------------------------------------------->
    public ResponseEntity<String> deletePriceElectricity(int id) {
        try {
            PriceElectricity pricebyid = priceElectricityRepository.findById(id).orElse(null);
            if (pricebyid == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            priceElectricityRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
