package poly.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.com.entity.Resident;
import poly.com.service.ResidentService;

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
    public ResponseEntity findById(@PathVariable int id) {
        return residentService.findById(id);
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody Resident resident) {
        return residentService.create(resident);
    }
}
