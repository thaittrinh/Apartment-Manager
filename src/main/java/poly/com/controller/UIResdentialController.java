package poly.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.com.repository.TypeVehicelRepository;


@Controller
@RequestMapping("/ui/resdential")
public class UIResdentialController {
    @Autowired
    TypeVehicelRepository typeVehicelRepository;
    // return template page table residential
    @GetMapping("")
    public String pageTableCudan( ModelMap model) {
        model.addAttribute("TypeVehicles", typeVehicelRepository.findAll());
        return "contents/quanly/cudan/table-Cudan";
    }


    @GetMapping("/vehicle")
    public String pageTableXe( ModelMap model) {
        model.addAttribute("TypeVehicles", typeVehicelRepository.findAll());
        return "contents/quanly/cudan/table-xe";
    }


}
