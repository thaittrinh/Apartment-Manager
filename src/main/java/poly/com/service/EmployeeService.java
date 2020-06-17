package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import poly.com.entity.Employee;
import poly.com.entity.Resident;
import poly.com.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    // < ------------------------------- Class Employee service -------------------->

    @Autowired
    EmployeeRepository employeeRepository;
    // --------------------------------------------

    // < --------------------- findAll ----------------------->
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employee = employeeRepository.findAll();
        return ResponseEntity.ok(employee);
    }
    public  ResponseEntity findById(int id ){
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            return ResponseEntity.ok(employee);
        }catch (Exception e){
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
