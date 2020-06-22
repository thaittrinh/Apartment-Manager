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
    @RequestMapping("/apartment")
    public String pageCanho() {
        return "contents/quanly/can-ho/table-canho";
    }

 
    // return template page table hoa don
    @RequestMapping("/bill")
    public String pageTableHoadon() {
        return "contents/quanly/table-Hoadon";
    }
    
   
   
    
    
    // return template page table phi rac
    @RequestMapping("price/garbage")
    public String pageTablePhirac() {
        return "contents/quanly/bang-gia/table-phirac";
    }
    
    // return template page table gia dien 
    @RequestMapping("price/electricity")
    public String pageTableGiadien() {
        return "contents/quanly/bang-gia/table-giadien";
    }
    // return template page table gia nuoc
    @RequestMapping("price/water")
    public String pageTableGianuoc() {
        return "contents/quanly/bang-gia/table-gianuoc";
    }
    
    // return template page table phi quan ly 
    @RequestMapping("price/management")
    public String pageTablePhiquanly() {
        return "contents/quanly/bang-gia/table-phiquanly";
    }
    // return template page table thong tin liên hệ  
    @RequestMapping("/contact")
    public String pageTableLienhe() {
        return "contents/quanly/lien-he/form-lienhe";
    }
    
    
    
}
