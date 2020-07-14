package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quan-ly/can-ho")
public class ApartmentController {
    // return template page can ho
    @GetMapping()
    public String pageCanho() {
        return "contents/quanly/can-ho/table-canho";
    }

}
