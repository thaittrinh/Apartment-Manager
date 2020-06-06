package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class UIController {
	  // return template page admin
    @RequestMapping()
    public String pageadmin() {
        return "layout-admin";
    }

   
    // return template page can ho
    @RequestMapping("/table-canho")
    public String pageCanho() {
        return "contents/quanly/table-canho";
    }

    // return template page  chu can ho
   @RequestMapping("/chucanho")
    public String pageChuCanho() {
        return "contents/quanly/chucanho/form-chucanho";
    }

    // return template page   table chu can ho
    @RequestMapping("/table-chucanho")
    public String pageTabelChuCanho() {
        return "contents/quanly/chucanho/table-chucanho";
    }

    // return template page residential
   @RequestMapping("/cudan")
    public String pageCudan() {
        return "contents/quanly/cudan/form-cudan";
    }

    // return template page table residential
   @RequestMapping("/table-cudan")
    public String pageTableCudan() {
        return "contents/quanly/cudan/table-Cudan";
    }

    // return template page table residential
    @RequestMapping("/table-xe")
    public String pageTablexe() {
        return "contents/quanly/table-xe";
    }


    // return template page table residential
    @RequestMapping("/table-hoadon")
    public String pageTableHoadon() {
        return "contents/quanly/table-Hoadon";
    }
}
