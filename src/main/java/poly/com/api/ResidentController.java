package poly.com.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.com.constant.URL_API;
import poly.com.dto.ResponseDTO;
import poly.com.entity.Resident;
import poly.com.service.ResidentService;

@RestController
@RequestMapping(URL_API.PRICE_RESIDENT)
public class ResidentController {
    // < -------------------------------- Class Residential RestController ----------------------------->
    @Autowired
    ResidentService residentService;
    // --------------------------------

    // < ---------------------- findAll ------------------------->
    @GetMapping()
    public ResponseEntity<ResponseDTO> findAll() {
        return residentService.findAll();
    }

    // < ------------------------- findById ---------------------->
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable int id) {
        return residentService.findById(id);
    }

    // < ----------------------------- Create --------------------->
    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody Resident resident) {
        return residentService.create(resident);
    }

    // < ----------------------------- Update --------------------->
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable int id, @Valid @RequestBody Resident resident) {
        return residentService.update(id, resident);
    }

    // < ----------------------------- Delete --------------------->
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {
        return residentService.delete(id);
    }
}
