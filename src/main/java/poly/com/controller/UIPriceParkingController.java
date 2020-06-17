package poly.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.com.repository.TypeVehicelRepository;

@Controller
@RequestMapping("/ui/price/vehicle")
public class UIPriceParkingController {

    @Autowired
    TypeVehicelRepository typeVehicelRepository;

    @GetMapping("")
    public String pageTableXe(ModelMap model) {

        model.addAttribute("TypeVehicles", typeVehicelRepository.findAll());
        return "contents/quanly/bang-gia/table-phixe";
    }


}
