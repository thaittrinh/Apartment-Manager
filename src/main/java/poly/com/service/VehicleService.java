package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.constant.MessageError;
import poly.com.constant.MessageSuccess;
import poly.com.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> findAll() {
        List<Vehicle> vehicles = vehicleRespository.findAll();
        return ResponseEntity.ok(new ResponseDTO(vehicles, null));
    }

    // <----------------------------- findById --------------------->
    public ResponseEntity<ResponseDTO> findById(int id) {
        try {
            Vehicle vehicle = vehicleRespository.findById(id).orElse(null);
            return ResponseEntity.ok(new ResponseDTO(vehicle, null));
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // < --------------------------- Create -------------------------->
    public ResponseEntity<ResponseDTO> create( Vehicle newVehicle){
       try {   
           newVehicle.setId(0);
           newVehicle = vehicleRespository.save(newVehicle);
           return ResponseEntity.ok(new ResponseDTO(newVehicle, MessageSuccess.INSERT_SUCCSESS));
       }catch (Exception e){
    	   return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    public  ResponseEntity<ResponseDTO> update(int id, Vehicle newVehicle){
        try {
            if(!vehicleRespository.existsById(id))
                return new  ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_VEHICEL), HttpStatus.NOT_FOUND);
            
            newVehicle.setId(id);
            newVehicle = vehicleRespository.save(newVehicle);
            return ResponseEntity.ok(new ResponseDTO(newVehicle, MessageSuccess.UPDATE_SUCCSESS));
        }catch (Exception e){
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public  ResponseEntity<ResponseDTO> delete(int id ){
        try {
            if (!vehicleRespository.existsById(id))
            	return new  ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_VEHICEL), HttpStatus.NOT_FOUND);
            vehicleRespository.deleteById(id);
            return ResponseEntity.ok(new ResponseDTO(null, MessageSuccess.DELETE_SUCCSESS));
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
