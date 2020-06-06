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
    @RequestMapping("/table-can-ho")
    public String pageCanho() {
        return "contents/quanly/table-canho";
    }

    // return template page  chu can ho
   @RequestMapping("/form-chu-can-ho")
    public String pageChuCanho() {
        return "contents/quanly/chucanho/form-chucanho";
    }

    // return template page   table chu can ho
    @RequestMapping("/table-chu-can-ho")
    public String pageTabelChuCanho() {
        return "contents/quanly/chucanho/table-chucanho";
    }

    // return template page residential
   @RequestMapping("/form-cu-dan")
    public String pageCudan() {
        return "contents/quanly/cudan/form-cudan";
    }

    // return template page table residential
   @RequestMapping("/table-cu-dan")
    public String pageTableCudan() {
        return "contents/quanly/cudan/table-Cudan";
    }

    // return template page table xe
    @RequestMapping("/table-xe")
    public String pageTableXe() {
        return "contents/quanly/table-xe";
    }


    // return template page table hoa don
    @RequestMapping("/table-hoa-don")
    public String pageTableHoadon() {
        return "contents/quanly/table-Hoadon";
    }
    
    // return template page table phi rac
    @RequestMapping("/table-phi-rac")
    public String pageTablePhirac() {
        return "contents/quanly/bang-gia/table-phirac";
    }
    
    // return template page table gia dien 
    @RequestMapping("/table-gia-dien")
    public String pageTableGiadien() {
        return "contents/quanly/bang-gia/table-giadien";
    }
    // return template page table gia nuoc
    @RequestMapping("/table-gia-nuoc")
    public String pageTableGianuoc() {
        return "contents/quanly/bang-gia/table-gianuoc";
    }
    
    // return template page table phi quan ly 
    @RequestMapping("/table-phi-quan-ly")
    public String pageTablePhiquanly() {
        return "contents/quanly/bang-gia/table-phiquanly";
    }
}
