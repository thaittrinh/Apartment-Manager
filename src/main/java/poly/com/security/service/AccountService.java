package poly.com.security.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import poly.com.entity.ERole;
import poly.com.entity.Employee;
import poly.com.entity.Role;
import poly.com.payload.request.LoginRequest;
import poly.com.payload.request.SignupRequest;
import poly.com.payload.response.JwtResponse;
import poly.com.payload.response.MessageResponse;
import poly.com.repository.EmployeeRepository;
import poly.com.repository.RoleRepository;
import poly.com.security.jwt.JwtUtils;


@Service
public class AccountService {

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
	
	public ResponseEntity<?> authenticateUser( LoginRequest loginRequest) {
		
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
	
	
	public ResponseEntity<?> registerUser( SignupRequest signUpRequest) {
		if (employeeRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
	
		Employee employee =  new Employee(signUpRequest.getId(), signUpRequest.getFullName(), signUpRequest.isGender(), signUpRequest.getBirthday(),
											signUpRequest.getIndentitycard(), signUpRequest.getPhone(), signUpRequest.getAddress(), signUpRequest.getEmail(),
											signUpRequest.getImage(), signUpRequest.getUsername(),
											passwordEncoder.encode(signUpRequest.getPassword()), null);

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
			
	}
	
	
}
