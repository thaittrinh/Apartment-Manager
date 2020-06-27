
package poly.com.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.com.dto.ResponseDTO;
import poly.com.security.request.ChangePasswordRequest;
import poly.com.security.request.LoginRequest;
import poly.com.security.service.AccountService;

/* *
 * Login, Logout, change password
 */
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

    @PutMapping("/change/password")
    public ResponseEntity<ResponseDTO> changepasssword(@Valid @RequestBody ChangePasswordRequest passwordRequest) {
        return accountService.changepassword(passwordRequest);
    }

}
