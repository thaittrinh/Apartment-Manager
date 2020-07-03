package poly.com.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trang-chu")
public class HomeController {

	@GetMapping
	public String homePage() {
		
		return "contents/user/home";
	}
	
	@GetMapping("/gioi-thieu")
	public String aboutUs() {
		
		return "contents/user/about";
	}
	
	@GetMapping("/tin-tuc")
	public String news() {
		
		return "contents/user/news";
	}
	
	@GetMapping("/thiet-ke")
	public String design() {
		
		return "contents/user/design";
	}
	
	@GetMapping("/noi-quy")
	public String roles() {
		
		return "contents/user/rules";
	}
	
}
