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
    public ResponseEntity<ResponseDTO> changepassword(int id, Employee employee) {
        if (!employeeRepository.existsById(id))
            return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_EMPLOYEE), HttpStatus.NOT_FOUND);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee employeePassword = employeeRepository.findByPassword(employee.getPassword());
        if (employeePassword.equals(employee.getPassword())) {
            System.out.println(employee.getPassword());
            employeeRepository.updatepassword(employee.getPassword(), id);
            return ResponseEntity.ok(new ResponseDTO(employeePassword, MessageSuccess.UPDATE_SUCCSESS));
        }
        return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
