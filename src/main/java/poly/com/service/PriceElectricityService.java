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
            return ResponseEntity.ok(priceElectricity);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ---------------------------- Create ------------------------------------>
    @SuppressWarnings("deprecation")
    public ResponseEntity<PriceElectricity> createPriceElectricity(PriceElectricity newPriceElectricity) {
        try {
            PriceElectricity priceElectricity = priceElectricityRepository.findByLimit(
                    newPriceElectricity.getDate().getYear() + 1900,
                    newPriceElectricity.getDate().getMonth() + 1,
                    newPriceElectricity.getLimits());
            if (priceElectricity != null)
                return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409

            newPriceElectricity.setId(0);
            newPriceElectricity = priceElectricityRepository.save(newPriceElectricity);
            return ResponseEntity.ok(newPriceElectricity);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ----------------------------- Update ------------------------------------->
    @SuppressWarnings("deprecation")
    public ResponseEntity<PriceElectricity> updatePriceElectricity(int id, PriceElectricity newPriceElectricity) {
        try {
            if (!priceElectricityRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            PriceElectricity priceElectricity = priceElectricityRepository.findByLimit(
                    newPriceElectricity.getDate().getYear() + 1900,
                    newPriceElectricity.getDate().getMonth() + 1,
                    newPriceElectricity.getLimits());
            if (priceElectricity != null && priceElectricity.getId() != id)
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);

            newPriceElectricity.setId(id);
            newPriceElectricity = priceElectricityRepository.save(newPriceElectricity);
            return ResponseEntity.ok(newPriceElectricity);
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
