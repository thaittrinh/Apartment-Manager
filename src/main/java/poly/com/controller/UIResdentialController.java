package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ui/resdential")
public class UIResdentialController {
    // return template page table residential
    @GetMapping()
    public String pageTableCudan() {
        return "contents/quanly/cudan/table-Cudan";
    }


    @GetMapping("/vehicle")
    public String pageTableXe() {
        return "contents/quanly/cudan/table-xe";
    }


}
