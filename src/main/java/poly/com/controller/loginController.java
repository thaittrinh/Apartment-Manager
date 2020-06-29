package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/authentication/account")
public class loginController {

    @GetMapping("/login")
    public String pageLogin() {
        return "contents/login/login.html";
    }
}
