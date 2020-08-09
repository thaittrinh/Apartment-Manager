package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poly.com.constant.MessageError;
import poly.com.constant.MessageSuccess;
import poly.com.dto.ResponseDTO;
import poly.com.entity.Resident;
import poly.com.helper.ResidentExportExcel;
import poly.com.repository.ApartmentRepository;
import poly.com.repository.ResidentRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResidentService {
    // < --------------------------------- Class Residential Service --------------------------------->
    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    ApartmentRepository apartmentRepository;


    // < ---------------------- findAll -------------------->
    public ResponseEntity<ResponseDTO> findAll() {
        List<Resident> residentials = residentRepository.findAll();
        return ResponseEntity.ok(new ResponseDTO(residentials, null));
    }

    // <------------------------- findById ------------------>
    public ResponseEntity<ResponseDTO> findById(int id) {
        try {
            Resident resident = residentRepository.findById(id).orElse(null);
            return ResponseEntity.ok(new ResponseDTO(resident, null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------- Create --------------------->
    public ResponseEntity<ResponseDTO> create(Resident newResident) {
        try {
            if (!apartmentRepository.existsById(newResident.getApartment().getId()))
                return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_APARTMENT), HttpStatus.NOT_FOUND);
            // --------------------------------------------------------
            if (newResident.getIdentitycard() != "") {
                Resident resident = residentRepository.findByIdentitycard(
                    newResident.getIdentitycard()).orElse(null);
                if (resident != null)
                    return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_409_IDENTICARD), HttpStatus.CONFLICT);
            } // ---------------------------------------------------
            else {
                newResident.setIdentitycard(null);
            }  //--------------------------------------------------
            newResident.setId(0);
            newResident = residentRepository.save(newResident);
            return ResponseEntity.ok(new ResponseDTO(newResident, MessageSuccess.INSERT_SUCCSESS));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < --------------------------- Update ---------------------->
    public ResponseEntity<ResponseDTO> update(int id, Resident newResident) {
        try {
            if (!apartmentRepository.existsById(newResident.getApartment().getId()))
                return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_APARTMENT), HttpStatus.NOT_FOUND);

            if (!residentRepository.existsById(id))
                return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_RESIDENT), HttpStatus.NOT_FOUND);
            // -------------------------------------
            if (newResident.getIdentitycard() != "") {
                Resident resident = residentRepository.findByIdentitycard(
                        newResident.getIdentitycard()).orElse(null);
                if (resident != null && resident.getId() != id)
                    return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_409_IDENTICARD), HttpStatus.CONFLICT);
            }
            // ----------------------------------------
            else {
                newResident.setIdentitycard(null);
            }
            // -----------------------------------------
            newResident.setId(id);
            newResident = residentRepository.save(newResident);
            return ResponseEntity.ok(new ResponseDTO(newResident, MessageSuccess.UPDATE_SUCCSESS));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ----------------------- delete --------------------------->
    public ResponseEntity<ResponseDTO> delete(int id) {
        try {
            if (!residentRepository.existsById(id))
                return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_RESIDENT), HttpStatus.NOT_FOUND);
            residentRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseDTO(null, MessageSuccess.DELETE_SUCCSESS));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* ------------------------ Export to EXCEL ----------------------- */
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachement; filename = OwnApartment.xlsx";
            response.setHeader(headerKey, headerValue);
            List<Resident> residentList = residentRepository.findAll();
            ResidentExportExcel residentExportExcel = new ResidentExportExcel(residentList);
            residentExportExcel.export(response);
            return new ResponseEntity<>(MessageSuccess.EXPORT_SUCCSESS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
