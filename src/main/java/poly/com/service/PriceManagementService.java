package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.PriceManagement;
import poly.com.repository.PriceManagementRepository;

import java.util.List;

@Service
public class PriceManagementService {

// < ----------------------------- Class Price Management Service ------------------------->

    @Autowired
    PriceManagementRepository priceManagementRepository;
// < ---------------------------------------------------

    // < ---------------------------- find All ------------------------------->
    public ResponseEntity<List<PriceManagement>> findAll() {
        List<PriceManagement> priceManagements = priceManagementRepository.findAll();
        return ResponseEntity.ok(priceManagements);
    }

    // < ----------------------------- find by Id ------------------------------ >
    public ResponseEntity<PriceManagement> findbyId(int id) {
        try {
            PriceManagement priceManagement = priceManagementRepository.findById(id).orElse(null);
            return ResponseEntity.ok(priceManagement);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------ Create --------------------------------->
    @SuppressWarnings("deprecation")
    public ResponseEntity<PriceManagement> createPriceManagement(PriceManagement priceManagement) {
        try {
            PriceManagement priceManagements = priceManagementRepository.findByYearAndMonth(
                    priceManagement.getDate().getYear() + 1900,
                    priceManagement.getDate().getMonth() + 1);

            if (priceManagements != null)
                return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409

            priceManagement.setId(0);
            PriceManagement management = priceManagementRepository.save(priceManagement);
            return ResponseEntity.ok(management);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // <-------------------------------- Update --------------------------------->
    @SuppressWarnings("deprecation")
    public ResponseEntity<PriceManagement> updatePriceManagement(int id, PriceManagement priceManagement) {
        try {
            if (!priceManagementRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            // Giá các tháng trước đã có
            PriceManagement priceManagements = priceManagementRepository.findByYearAndMonth(
                    priceManagement.getDate().getYear() + 1900,
                    priceManagement.getDate().getMonth() + 1);
            if (priceManagements != null && id != priceManagements.getId())
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);

            priceManagement.setId(id);
            priceManagement = priceManagementRepository.save(priceManagement);
            return ResponseEntity.ok(priceManagement);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------- Delete ----------------------------------->
    public ResponseEntity<String> deletePriceManagemet(int id) {
        try {
            if (!priceManagementRepository.existsById(id))
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            priceManagementRepository.deleteById(id);
            return ResponseEntity.ok("Delete success!");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
