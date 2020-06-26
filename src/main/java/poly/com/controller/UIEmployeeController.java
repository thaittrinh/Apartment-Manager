package poly.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/employee")
public class UIEmployeeController {

    @GetMapping()
    public String pageEmployee(){
        return "contents/quanly/nhan-vien/table-nhanvien";
    }
    
    // return template page chu can ho
 	@GetMapping("/form/{id}")
 	public String formUpdate(@PathVariable int id, ModelMap model) {
 			model.addAttribute("idEmployee" , id);
 		return "contents/quanly/nhan-vien/form-update";
 	}
    
    
    
}
