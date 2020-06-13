package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.Resident;
import poly.com.repository.ResidentRepository;

import java.util.List;

@Service
public class ResidentService {
    // < --------------------------------- Class Residential Service --------------------------------->
    @Autowired
    ResidentRepository residentRepository;
    // ---------------------------------------------

    // < ---------------------- findAll -------------------->
    public ResponseEntity<List<Resident>> findAll() {
        List<Resident> residentials = residentRepository.findAll();
        return ResponseEntity.ok(residentials);
    }

    // <------------------------- findById ------------------>
    public ResponseEntity findById(int id) {
        try {
            Resident resident = residentRepository.findById(id).orElse(null);
            return ResponseEntity.ok(resident);

        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity create(Resident resident) {
        try {
            if (resident.getIdentitycard() != null && residentRepository.existsByIdentitycard(
                    resident.getIdentitycard()))
                return new ResponseEntity(null, HttpStatus.CONFLICT);

            resident.setId(0);
            resident = residentRepository.save(resident);
            return ResponseEntity.ok(resident);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // < --------------------------- Update ---------------------->
    public ResponseEntity update(int id, Resident resident) {
        try {
            if (!residentRepository.existsById(id))
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            if (resident.getIdentitycard() != null && residentRepository.existsByIdentitycard(
                    resident.getIdentitycard()))
                return new ResponseEntity(null, HttpStatus.CONFLICT);

            resident.setId(id);
            resident = residentRepository.save(resident);
            return ResponseEntity.ok(resident);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ----------------------- delete --------------------------->
    public ResponseEntity delete(int id) {
        try {
            if (!residentRepository.existsById(id))
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            residentRepository.deleteById(id);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}