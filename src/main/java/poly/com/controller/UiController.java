package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import poly.com.entity.Employee;

@Controller
@RequestMapping("/quan-ly")
public class UiController {

    // return template page admin
    @RequestMapping("/welcome")
    public String pageadmin() {
        return "layout-admin";
    }

    // return template page table thông báo
    @GetMapping("/noi-quy")
    public String pageRegulation() {
        return "contents/quanly/noi-quy/noiquy";
    }

    // return template page table thông báo
    @GetMapping("/thong-bao")
    public String pageNotification() {
        return "contents/quanly/thong-bao/thongbao";
    }

    // return template page form table thông báo
    @GetMapping("/thong-bao/{id}")
    public String pageFormNotification(@PathVariable int id, ModelMap model) {
        model.addAttribute("id", id);
        return "contents/quanly/thong-bao/form-thongbao";
    }
    
    @GetMapping("/thong-tin-tai-khoan")
    public  String pageFormAccount(){
        return  "contents/quanly/nhan-vien/form-account";
    }


}
