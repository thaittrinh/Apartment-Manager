package poly.com.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//link tham khảo : https://loda.me/khai-niem-cross-origin-resource-sharing-cors-va-cach-fix-loda1554795977503/
//cho phép các request khác domain request tới.
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	//@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	
	@GetMapping("/mod")
//	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}
	
	@GetMapping("/admin")
//	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
