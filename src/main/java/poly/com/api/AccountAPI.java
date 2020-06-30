package poly.com.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import poly.com.dto.ResponseDTO;
import poly.com.service.EmployeeService;

@RestController
@RequestMapping("api/account")
public class AccountAPI {

	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping("/{username}")
    public ResponseEntity<ResponseDTO> findByUsername(@PathVariable String username) {
      
    	return employeeService.findByUsername(username);
    }
	
	 @PostMapping("/upload-file/{id}")
	    public ResponseEntity<ResponseDTO> uploadFile(@PathVariable int id, @RequestParam("file") MultipartFile mFile) {
	        return employeeService.uploadFile(mFile, id);
	 }
	
	
    
}
