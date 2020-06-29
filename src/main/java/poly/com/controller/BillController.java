package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quan-ly/hoa-don")
public class BillController {

	 // return template page table hoa don
    @GetMapping()
    public String pageTableHoadon() {
        return "contents/quanly/hoa-don/table-hoadon";
    }
    
    
    @GetMapping("/{id}")
    public String pageUpdate(@PathVariable int id, ModelMap model){
    	
    	model.addAttribute("id", id);
        return "contents/quanly/hoa-don/detail";
    }
    
    
    
	
}
