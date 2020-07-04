
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poly.com.dto.ResponseDTO;
import poly.com.request.EmployeeRequest;
import poly.com.security.request.ChangePasswordRequest;
import poly.com.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeAPI {

    @Autowired
    EmployeeService employeeService;

    // <-------------------------- findAll ----------------------------->
    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        return employeeService.findAll();
    }

    // <--------------------- findByID -------------------------------->
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable int id) {
        return employeeService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody EmployeeRequest signUpRequest) {
        return employeeService.insertEmployee(signUpRequest);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable int id, @Valid @RequestBody EmployeeRequest employeeRequest) {

        return employeeService.updateEmployee(id, employeeRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

    @PostMapping("/upload-file/{id}")
    public ResponseEntity<ResponseDTO> uploadFile(@PathVariable int id, @RequestParam("file") MultipartFile mFile) {
        return employeeService.uploadFile(mFile, id);
    }

    @PutMapping("/change-password")
    public ResponseEntity<ResponseDTO> changepassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return employeeService.changepassword(changePasswordRequest);
    }


}
