package poly.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.com.repository.TypeVehicelRepository;

@Controller
@RequestMapping("/ui/quan-ly/bang-gia")
public class TablePriceController {

    @Autowired
    TypeVehicelRepository typeVehicelRepository;

    // return template page table phi rac
    @RequestMapping("/bang-phi-rac")
    public String pageTablePhirac() {
        return "contents/quanly/bang-gia/table-phirac";
    }

    // return template page table gia dien 
    @RequestMapping("/bang-gia-dien")
    public String pageTableGiadien() {
        return "contents/quanly/bang-gia/table-giadien";
    }

    // return template page table gia nuoc
    @RequestMapping("/bang-gia-nuoc")
    public String pageTableGianuoc() {
        return "contents/quanly/bang-gia/table-gianuoc";
    }

    // return template page table phi quan ly 
    @RequestMapping("/bang-gia-phi-quan-ly-chung-cu")
    public String pageTablePhiquanly() {
        return "contents/quanly/bang-gia/table-phiquanly";
    }

    @GetMapping("/bang-gia-phi-ky-gui-xe")
    public String pageTableXe(ModelMap model) {
        model.addAttribute("TypeVehicles", typeVehicelRepository.findAll());
        return "contents/quanly/bang-gia/table-phixe";
    }

}
