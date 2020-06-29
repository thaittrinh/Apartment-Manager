package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/ui/notification")
public class NotificationController {
	
	// return template page table thông báo  
    @GetMapping()
    public String pageNotification() {
        return "contents/quanly/thong-bao/thongbao";
    }
    
 // return template page form table thông báo  
    @GetMapping("/form/{id}")
    public String pageFormNotification(@PathVariable int id, ModelMap model) {
    	model.addAttribute("id", id);
        return "contents/quanly/thong-bao/form-thongbao";
    }
    
}