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
    @RequestMapping("/table-apartment")
    public String pageCanho() {
        return "contents/quanly/table-canho";
    }

    // return template page  chu can ho
   @RequestMapping("/form-own-apartment")
    public String pageChuCanho() {
        return "contents/quanly/chucanho/form-chucanho";
    }

    // return template page   table chu can ho
    @RequestMapping("/table-own-apartment")
    public String pageTabelChuCanho() {
        return "contents/quanly/chucanho/table-chucanho";
    }

    // return template page residential
   @RequestMapping("/form-resdential")
    public String pageCudan() {
        return "contents/quanly/cudan/form-cudan";
    }

    // return template page table residential
   @RequestMapping("/table-resdential")
    public String pageTableCudan() {
        return "contents/quanly/cudan/table-Cudan";
    }

    // return template page table xe
    @RequestMapping("/table-vehicle")
    public String pageTableXe() {
        return "contents/quanly/table-xe";
    }


    // return template page table hoa don
    @RequestMapping("/table-bill")
    public String pageTableHoadon() {
        return "contents/quanly/table-Hoadon";
    }
    
    // return template page table phi rac
    @RequestMapping("/table-price-garbage")
    public String pageTablePhirac() {
        return "contents/quanly/bang-gia/table-phirac";
    }
    
    // return template page table gia dien 
    @RequestMapping("/table-price-electricity")
    public String pageTableGiadien() {
        return "contents/quanly/bang-gia/table-giadien";
    }
    // return template page table gia nuoc
    @RequestMapping("/table-price-water")
    public String pageTableGianuoc() {
        return "contents/quanly/bang-gia/table-gianuoc";
    }
    
    // return template page table phi quan ly 
    @RequestMapping("/table-price-management")
    public String pageTablePhiquanly() {
        return "contents/quanly/bang-gia/table-phiquanly";
    }
    // return template page table thong tin liên hệ  
    @RequestMapping("/table-contact")
    public String pageTableLienhe() {
        return "contents/quanly/table-lienhe";
    }
    
}
