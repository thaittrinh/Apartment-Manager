
package poly.com.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import poly.com.entity.Employee;
import poly.com.entity.OwnApartment;
import poly.com.payload.request.LoginRequest;
import poly.com.payload.request.SigninRequest;
import poly.com.security.service.AccountService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AccountService accountService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return accountService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SigninRequest signUpRequest) {

        return accountService.registerUser(signUpRequest);

    }

    // <-------------------------- findAll ----------------------------->
    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        return accountService.findAll();
    }

    // <--------------------- findByID -------------------------------->
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        return accountService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody SigninRequest signUpRequest, @PathVariable int id) {
        return accountService.update(id, signUpRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return accountService.delete(id);
    }

    @PostMapping("/upload-file/{id}")
    public ResponseEntity<Employee> uploadFile(@PathVariable int id, @RequestParam("file") MultipartFile mFile) {
        return accountService.uploadFile(mFile, id);
    }

}
