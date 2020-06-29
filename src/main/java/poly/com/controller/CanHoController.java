package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/quan-ly/")
public class CanHoController {
    // return template page can ho
    @RequestMapping("/can-ho")
    public String pageCanho() {
        return "contents/quanly/can-ho/table-canho";
    }

}
