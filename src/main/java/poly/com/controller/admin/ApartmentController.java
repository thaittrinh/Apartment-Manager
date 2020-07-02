package poly.com.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quan-ly/can-ho")
public class ApartmentController {
    // return template page can ho
    @RequestMapping()
    public String pageCanho() {
        return "contents/quanly/can-ho/table-canho";
    }

}
