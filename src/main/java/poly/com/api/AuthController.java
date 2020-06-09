package poly.com.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.com.entity.Account;
import poly.com.entity.ERole;
import poly.com.entity.Role;
import poly.com.entity.Employee;
import poly.com.payload.request.LoginRequest;
import poly.com.payload.request.SignupRequest;
import poly.com.payload.response.JwtResponse;
import poly.com.payload.response.MessageResponse;
import poly.com.repository.AccountRepository;
import poly.com.repository.RoleRepository;
import poly.com.repository.EmployeeRepository;
import poly.com.security.jwt.JwtUtils;
import poly.com.security.service.AccountDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	EmployeeRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Employee user = null;

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		AccountDetailsImpl accontDetails = (AccountDetailsImpl) authentication.getPrincipal();
		List<String> roles = accontDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		if (accontDetails != null) {
			user = userRepository.findById((int) accontDetails.getId())
					.orElseThrow(() -> new UsernameNotFoundException("\"User Not Found with username: \" + username"));
		}

		return ResponseEntity.ok(
				new JwtResponse(jwt, accontDetails.getId(), accontDetails.getUsername(), user.getFullName(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new account
		Employee user = new Employee();
		user.setId(signUpRequest.getId());
		Account account = new Account(0, user, signUpRequest.getUsername(),
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

		account.setRoles(roles);
		accountRepository.save(account);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
