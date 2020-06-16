package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.Vehicle;
import poly.com.repository.VehicleRespository;

import java.util.List;

@Service
public class VehicleService {
    // < ------------------------------ Class Vehicle Service ----------------------->
    @Autowired
    VehicleRespository vehicleRespository;
    // -------------------------------

    //< ----------------------------- FindAll ------------------------->
    public ResponseEntity<List<Vehicle>> findAll() {
        List<Vehicle> vehicles = vehicleRespository.findAll();
        return ResponseEntity.ok(vehicles);
    }

    // <----------------------------- findById --------------------->
    public ResponseEntity findById(int id) {
        try {
            Vehicle vehicle = vehicleRespository.findById(id).orElse(null);
            return ResponseEntity.ok(vehicle);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // < --------------------------- Create -------------------------->
    public ResponseEntity create( Vehicle newVehicle){
       try {
           Vehicle vehicle = vehicleRespository.findByLicensePlates(
                   newVehicle.getLicensePlates()).orElse(null);
           if (vehicle != null)
               return new  ResponseEntity(null,HttpStatus.CONFLICT);
           newVehicle.setId(0);
           newVehicle = vehicleRespository.save(newVehicle);
           return ResponseEntity.ok(newVehicle);
       }catch (Exception e){
           return  new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    public  ResponseEntity update(int id, Vehicle newVehicle){
        try {
            if(!vehicleRespository.existsById(id))
                return new  ResponseEntity(null, HttpStatus.NOT_FOUND);
            Vehicle vehicle = vehicleRespository.findByLicensePlates( newVehicle.getLicensePlates()).orElse(null);
            if (vehicle != null && vehicle.getId() != id )
                return  new ResponseEntity(null, HttpStatus.CONFLICT);
            newVehicle.setId(id);
            newVehicle = vehicleRespository.save(newVehicle);
            return ResponseEntity.ok(newVehicle);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public  ResponseEntity delete(int id ){
        try {
            if (!vehicleRespository.existsById(id))
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            vehicleRespository.deleteById(id);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
