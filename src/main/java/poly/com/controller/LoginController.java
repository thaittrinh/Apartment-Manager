package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller

public class LoginController {

	@RequestMapping("/authentication/account/login")
    public String pageLogin() {
        return "contents/login/login";
    }
    
	@RequestMapping("/403")
    public String page403() {
		
        return "contents/403";
    }
    
}
