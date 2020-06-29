package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/regulation")
public class RegulationController {
	// return template page table thông báo  
    @GetMapping()
    public String pageRegulation() {
        return "contents/quanly/noi-quy/noiquy";
    }
    
 

}
