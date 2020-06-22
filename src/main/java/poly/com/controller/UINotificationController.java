package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/ui/notification")
public class UINotificationController {
	
	// return template page table thông báo  
    @GetMapping()
    public String pageNotification() {
        return "contents/quanly/thong-bao/thongbao";
    }
    
 // return template page form table thông báo  
    @GetMapping("/form")
    public String pageFormNotification() {
        return "contents/quanly/thong-bao/form-thongbao";
    }
    
}
