package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/quan-ly")
public class UiController {

    // return template page admin
    @RequestMapping()
    public String pageadmin() {
        return "layout-admin";
    }

    // return template page table thông báo
    @GetMapping("/noi-quy")
    public String pageRegulation() {
        return "contents/quanly/noi-quy/noiquy";
    }

    // return template page table thong tin liên hệ
    @RequestMapping("/lien-he")
    public String pageTableLienhe() {
        return "contents/quanly/lien-he/form-lienhe";
    }

    // return template page table thông báo
    @GetMapping("/danh-sach-thong-bao")
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