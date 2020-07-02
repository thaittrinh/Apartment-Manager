package poly.com.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trang-chu")
public class HomeController {

	@GetMapping
	public String homePage() {
		
		return "layout-user";
	}
	
}
