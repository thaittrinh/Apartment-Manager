package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class UIEmployeeController {

    @RequestMapping("/employee")
    public String pageEmployee(){
        return "contents/quanly/nhanvien/table-nhanvien";
    }
}
