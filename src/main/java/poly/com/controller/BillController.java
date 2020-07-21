package poly.com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;

import poly.com.entity.Bill;
import poly.com.helper.BillExportPDF;
import poly.com.repository.BillRepository;

@Controller
@RequestMapping("/quan-ly/hoa-don")
public class BillController {

	@Autowired
	BillRepository billRepository;
	
	
	 // return template page table hoa don
    @GetMapping()
    public String pageTableHoadon() {
        return "quanly/hoa-don/table-hoa-don.html";
    }
    
    
    @GetMapping("/{id}")
    public String pageUpdate(@PathVariable int id, ModelMap model){
    	
    	model.addAttribute("id", id);
        return "quanly/hoa-don/hoa-don-chi-tiet";
    }
    
    @GetMapping("/export-pdf")
    public void exportPdf(HttpServletResponse response, @RequestParam("id") int id) throws DocumentException, IOException{ 	
    	response.setContentType("application/pdf; charset=utf-8");
          response.setCharacterEncoding("UTF-8");
    	 String headerKey = "Content-Disposition";
         String headerValue = "attachement; filename=bill.pdf";
         response.setHeader(headerKey, headerValue);
         Bill bill =  billRepository.findById(id).orElse(null);
    	 BillExportPDF  billPDF = new BillExportPDF(bill);
    	 billPDF.export(response);
    }
}
