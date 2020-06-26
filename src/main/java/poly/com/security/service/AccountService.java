package poly.com.security.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import poly.com.constant.MessageError;
import poly.com.constant.MessageSuccess;
import poly.com.dto.ResponseDTO;
import poly.com.entity.Employee;
import poly.com.repository.EmployeeRepository;
import poly.com.repository.RoleRepository;
import poly.com.security.jwt.JwtUtils;
import poly.com.security.request.ChangePasswordRequest;
import poly.com.security.request.LoginRequest;
import poly.com.security.response.JwtResponse;


@Service
public class AccountService {

    // < ------------------------------------ Account Service -------------------->
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;


    //< ------------------------------------------- AuthenticateUser --------------------------------------->
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        Employee user = null;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AccountDetailsImpl accontDetails = (AccountDetailsImpl) authentication.getPrincipal();
        List<String> roles = accontDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        if (accontDetails != null) {
            user = employeeRepository.findById((int) accontDetails.getId()).orElse(null);
        }

        return ResponseEntity.ok(
                new JwtResponse(jwt, accontDetails.getId(), accontDetails.getUsername(), user.getFullName(), roles));
    }

    /*------------------------------------------  change password -------------------------------------*/
    public ResponseEntity<ResponseDTO> changepassword(ChangePasswordRequest passwordRequest) {
        try {
            Employee employee = employeeRepository.findById(passwordRequest.getId()).orElse(null);
            if (employee == null)
                return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_EMPLOYEE), HttpStatus.NOT_FOUND);
            String oldPassword = passwordRequest.getPassword();
            String dbPassword = employee.getPassword();
            if (passwordEncoder.matches(oldPassword, dbPassword)) {
                employee.setPassword(passwordEncoder.encode(passwordRequest.getNewpassword()));
                employee = employeeRepository.save(employee);
                return ResponseEntity.ok(new ResponseDTO(null, MessageSuccess.UPDATE_PASSWORD_SUCCSESS));
            } else {
                return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_EMPLOYEE_PASSWORD), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
            /* oldPassword nhận password của client truyền về server ( không mã hóa )
             * dbPassword lấy password từ database ( đã mã hóa )
             *   passwordEncoder.matches(Boolean) so sánh 2 chuỗi  oldpassword với dbpassword
             * nếu kết quả passwordEncoder.matches là true  thì mã hóa  lại mật khẩu mới và lưu vào database
             *  nếu kết quả passwordEncoder.matches là fale thì trả về status code 400 ,mật khẩu cũ không đúng  */
    }
}
