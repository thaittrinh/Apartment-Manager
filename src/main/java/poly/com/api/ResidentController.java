package poly.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.com.entity.Resident;
import poly.com.service.ResidentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/resident")
public class ResidentController {
    // < -------------------------------- Class Residential RestController ----------------------------->
    @Autowired
    ResidentService residentService;
    // --------------------------------

    // < ---------------------- findAll ------------------------->
    @GetMapping()
    public ResponseEntity<List<Resident>> findAll() {
        return residentService.findAll();
    }

    // < ------------------------- findById ---------------------->
    @GetMapping("/{id}")
    public ResponseEntity<Resident> findById(@PathVariable int id) {
        return residentService.findById(id);
    }

    // < ----------------------------- Create --------------------->
    @PostMapping()
    public ResponseEntity<Resident> create(@Valid @RequestBody Resident resident) {
        return residentService.create(resident);
    }

    // < ----------------------------- Update --------------------->
    @PutMapping("/{id}")
    public ResponseEntity<Resident> update(@PathVariable int id, @Valid @RequestBody Resident resident) {
        return residentService.update(id, resident);
    }

    // < ----------------------------- Delete --------------------->
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        return residentService.delete(id);
    }
}
