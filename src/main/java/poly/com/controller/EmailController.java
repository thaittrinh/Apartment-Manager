package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quan-ly/email")
public class EmailController {

	@GetMapping()
	public String formEmail() {
		
		 return "contents/quanly/email";
	}
}
