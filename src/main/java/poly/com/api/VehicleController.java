package poly.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.com.entity.Resident;
import poly.com.entity.Vehicle;
import poly.com.service.VehicleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    // < --------------------------------- Class RestController Vehicle ----------------------->
    @Autowired
    VehicleService vehicleService;
    // ----------------------------------------

    // < ------------------------ findAll ------------------>
    @GetMapping()
    public ResponseEntity<List<Vehicle>> findAll() {
        return vehicleService.findAll();
    }

    // < ------------------------- findById ---------------------->
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        return vehicleService.findById(id);
    }

    // < ----------------------------- Create --------------------->
    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.create(vehicle);
    }

    // < ----------------------------- Update --------------------->
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Vehicle vehicle) {
        return vehicleService.update(id, vehicle);
    }

    // < ----------------------------- Delete --------------------->
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        return vehicleService.delete(id);
    }

}
