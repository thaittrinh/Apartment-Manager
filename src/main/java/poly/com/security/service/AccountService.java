package poly.com.security.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import poly.com.entity.ERole;
import poly.com.entity.Employee;
import poly.com.entity.Resident;
import poly.com.entity.Role;
import poly.com.helper.FileHelper;
import poly.com.payload.request.LoginRequest;
import poly.com.payload.request.SigninRequest;
import poly.com.payload.response.JwtResponse;
import poly.com.payload.response.MessageResponse;
import poly.com.repository.EmployeeRepository;
import poly.com.repository.RoleRepository;
import poly.com.security.jwt.JwtUtils;

import javax.validation.Valid;


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

    @Autowired
    FileHelper fileHelper;
// < --------------------------------------------------------------------

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
            user = employeeRepository.findById((int) accontDetails.getId())
                    .orElseThrow(() -> new UsernameNotFoundException("\"User Not Found with username: \" + username"));
        }

        return ResponseEntity.ok(
                new JwtResponse(jwt, accontDetails.getId(), accontDetails.getUsername(), user.getFullName(), roles));
    }

    // <----------------------------------- find All ----------------------------->
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }

    // < ------------------------------- findById ------------------------------->
    public ResponseEntity<Employee> findById(int id) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ---------------------------------------- Register User --------------------------------------------->
    public ResponseEntity<?> registerUser(@Valid SigninRequest signUpRequest) {
        try {
            // < -----------------  check conflict ----------------------->
            Employee phone = employeeRepository
                    .findByPhone(signUpRequest.getPhone()).orElse(null);
            Employee indentiyCard = employeeRepository
                    .findByIndentitycard(signUpRequest.getIndentitycard()).orElse(null);

            if (phone != null)
                return ResponseEntity.status(409).body(new MessageResponse("Error: Phone already exist"));

            if (indentiyCard != null)
                return ResponseEntity.status(409).body(new MessageResponse("Error: IdentityCard already exist"));

            if (employeeRepository.existsByUsername(signUpRequest.getUsername()))
                return ResponseEntity.status(409).body(new MessageResponse("Error: Username already exist"));

            // --------------------------------------------------------------
            Employee employee = new Employee(
                    signUpRequest.getId(),
                    signUpRequest.getFullName(),
                    signUpRequest.isGender(),
                    signUpRequest.getBirthday(),
                    signUpRequest.getIndentitycard(),
                    signUpRequest.getPhone(),
                    signUpRequest.getAddress(),
                    signUpRequest.getEmail(),
                    signUpRequest.getImage(),
                    signUpRequest.getUsername(),
                    passwordEncoder.encode(signUpRequest.getPassword()), null);

            // ----------------------------- Role ----------------------------->
            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;

                        case "mod":
                            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;

                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            employee.setRoles(roles);
            employeeRepository.save(employee);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse("Error: INTERNAL_SERVER_ERROR"));
        }

    }

    // < ------------------------------------ Update User ----------------------------------------->
    public ResponseEntity<?> update(int id, SigninRequest signUpRequest) {
        try {
            // < -----------------  check conflict ----------------------->
            Employee phone = employeeRepository
                    .findByPhone(signUpRequest.getPhone()).orElse(null);
            Employee indentiyCard = employeeRepository
                    .findByIndentitycard(signUpRequest.getIndentitycard()).orElse(null);
            Employee username = employeeRepository
                    .findByUsername(signUpRequest.getUsername()).orElse(null);

            if (phone != null && phone.getId() != id)
                return ResponseEntity.status(409).body(new MessageResponse("Error: Phone already exist"));

            if (indentiyCard != null && indentiyCard.getId() != id)
                return ResponseEntity.status(409).body(new MessageResponse("Error: IdentityCard already exist"));

            if (username != null && username.getId() != id)
                return ResponseEntity.status(409).body(new MessageResponse("Error: Username already exist"));

            // --------------------------------------------------------------
            Employee employee = new Employee(
                    signUpRequest.getId(),
                    signUpRequest.getFullName(),
                    signUpRequest.isGender(),
                    signUpRequest.getBirthday(),
                    signUpRequest.getIndentitycard(),
                    signUpRequest.getPhone(),
                    signUpRequest.getAddress(),
                    signUpRequest.getEmail(),
                    signUpRequest.getImage(),
                    signUpRequest.getUsername(),
                    passwordEncoder.encode(signUpRequest.getPassword()), null);

            // ----------------------------- Role ----------------------------->
            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;

                        case "mod":
                            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;

                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            employee.setRoles(roles);
            employee.setId(id);
            employee = employeeRepository.save(employee);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse("Error: INTERNAL_SERVER_ERROR"));
        }
    }

    /// < ------------------------- Delete User --------------------- >
    public ResponseEntity<?> delete(int id) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null)
                return ResponseEntity.status(404).body(new MessageResponse("Error:User dose not exist"));
            employeeRepository.deleteById(id);
            fileHelper.deleteFile(employee.getImage());
            return ResponseEntity.status(200).body(new MessageResponse("Success: Delete user success"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse("Error: INTERNAL_SERVER_ERROR"));
        }
    }

    // < ------------------------------------ upload image ---------------------------->
    public ResponseEntity<Employee> uploadFile(MultipartFile mfile, int id) {
        if (mfile.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            fileHelper.deleteFile(employee.getImage());
            String fileName = fileHelper.saveFile(mfile, "user" + id);
            employee.setImage(fileName);
            employee = employeeRepository.save(employee);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
