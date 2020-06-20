package poly.com.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poly.com.constant.MessageError;
import poly.com.constant.MessageSuccess;
import poly.com.dto.ResponseDTO;
import poly.com.entity.ERole;
import poly.com.entity.Employee;
import poly.com.entity.Role;
import poly.com.helper.FileHelper;
import poly.com.repository.EmployeeRepository;
import poly.com.repository.RoleRepository;
import poly.com.request.EmployeeRequest;

@Service
public class EmployeeService {

	 @Autowired
	 EmployeeRepository employeeRepository;
	 
	 @Autowired
	 RoleRepository roleRepository;

	 @Autowired
	 PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 FileHelper fileHelper;
	 
    // <----------------------------------- find All ----------------------------->
    public ResponseEntity<ResponseDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
               
        return ResponseEntity.ok(new ResponseDTO(employees, null));
    }

    // < ------------------------------- findById ------------------------------->
    public ResponseEntity<ResponseDTO> findById(int id) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            return ResponseEntity.ok(new ResponseDTO(employee, null));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ---------------------------------------- Insert --------------------------------------------->
    public ResponseEntity<ResponseDTO> insertEmployee( EmployeeRequest signUpRequest) {
    	System.out.println(signUpRequest);
        try {
        	ResponseEntity<ResponseDTO> reponseConflict = checkConflict(0, signUpRequest.getUsername(),
        													   signUpRequest.getPhone(), signUpRequest.getIndentitycard());
        	if(reponseConflict != null)
        		        return reponseConflict ;
        	System.out.println(1);
        	
            Employee employee = new Employee( 0, signUpRequest.getFullName(), signUpRequest.isGender(),
                    				signUpRequest.getBirthday(), signUpRequest.getIndentitycard(), signUpRequest.getPhone(),
                    				signUpRequest.getAddress(), signUpRequest.getEmail(), signUpRequest.getImage(),signUpRequest.getUsername(),
                    				passwordEncoder.encode(signUpRequest.getPassword()), null);

            // ----------------------------- Roles -----------------------------> 
            employee.setRoles(getRoles(signUpRequest.getRoles()));
            System.out.println(2);
            employee =  employeeRepository.save(employee);
            
            return ResponseEntity.ok(new ResponseDTO(employee, MessageSuccess.INSERT_SUCCSESS));
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);		
        }
    }
  
    
    // < ------------------------------------ Update ----------------------------------------->
    public ResponseEntity<ResponseDTO> updateEmployee(int id, EmployeeRequest employeeRequest) {
        try {
        	if (!employeeRepository.existsById(id)) 
        		return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_EMPLOYEE), HttpStatus.NOT_FOUND);
		
            // < -----------------  check conflict ----------------------->
        	ResponseEntity<ResponseDTO>  reponseConflict = checkConflict(id, employeeRequest.getUsername(),
        			employeeRequest.getPhone(), employeeRequest.getIndentitycard());
			if(reponseConflict != null)
				return reponseConflict ;
            // --------------------------------------------------------------
            Employee employee = new Employee( employeeRequest.getId(), employeeRequest.getFullName(), employeeRequest.isGender(),
            						employeeRequest.getBirthday(), employeeRequest.getIndentitycard(), employeeRequest.getPhone(),
            						employeeRequest.getAddress(), employeeRequest.getEmail(), employeeRequest.getImage(), employeeRequest.getUsername(),
	        						passwordEncoder.encode(employeeRequest.getPassword()), null);

            // ----------------------------- Role ----------------------------->      
            System.out.println(employeeRequest.getRoles());
            employee.setRoles(getRoles(employeeRequest.getRoles()));
            employee = employeeRepository.save(employee);
            return ResponseEntity.ok(new ResponseDTO(employee, MessageSuccess.UPDATE_SUCCSESS));
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /// < ------------------------- Delete User --------------------- >
    public ResponseEntity<ResponseDTO> deleteEmployee(int id) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null)
            	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_EMPLOYEE), HttpStatus.NOT_FOUND);
            
            employeeRepository.deleteById(id);
            fileHelper.deleteFile(employee.getImage());
            return ResponseEntity.ok(new ResponseDTO(null, MessageSuccess.DELETE_SUCCSESS));
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // < ------------------------------------ upload image ---------------------------->
    public ResponseEntity<ResponseDTO>  uploadFile(MultipartFile mfile, int id) {
        if (mfile.isEmpty())
            return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_400), HttpStatus.BAD_REQUEST);
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null)
            	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_404_EMPLOYEE), HttpStatus.NOT_FOUND);
            fileHelper.deleteFile(employee.getImage());
            String fileName = fileHelper.saveFile(mfile, "admin" + id);
            employee.setImage(fileName);
            employee = employeeRepository.save(employee);
            return ResponseEntity.ok(new ResponseDTO(null, MessageSuccess.UPLOAD_FILE_SUCCSESS));
        } catch (Exception e) {
        	return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    public ResponseEntity<ResponseDTO> checkConflict(int id, String username, String phone, String indentitycard) {
    	try {		
    		Employee employee_phone = employeeRepository.findByPhone(phone).orElse(null);	
    		  if (employee_phone != null && employee_phone.getId() != id)            	
              	return ResponseEntity.status(409).body(new ResponseDTO(null, MessageError.ERROR_409_PHONE));
    		
            Employee employee_indentiyCard = employeeRepository.findByIndentitycard(indentitycard).orElse(null);
            if (employee_indentiyCard != null && employee_indentiyCard.getId() != id)
           	 return ResponseEntity.status(409).body(new ResponseDTO(null, MessageError.ERROR_409_IDENTICARD));
            
            Employee employee_username = employeeRepository.findByUsername(username).orElse(null);
            if (employee_username != null && employee_username.getId() != id)
            	 return ResponseEntity.status(409).body(new ResponseDTO(null, MessageError.ERROR_409_USERNAME));
            
            return null;
		} catch (Exception e) {
			 return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    }
    
    public Set<Role> getRoles( Set<String> strRoles) {
    	  Set<Role> roles = new HashSet<>();
          if (strRoles == null) {
        	  System.out.println(1);
              return null;
          } else {  	  
              strRoles.forEach(role -> {
                  switch (role) {
                      case "admin":
                          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                  .orElseThrow(() -> new RuntimeException());
                          roles.add(adminRole);
                          break;

                      case "mod":
                          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                  .orElseThrow(() -> new RuntimeException());
                          roles.add(modRole);
                          break;
                      case "user":
                    	  Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                          .orElseThrow(() -> new RuntimeException());
                    	  roles.add(userRole);
                    	  break;
                      default:
                      	throw new RuntimeException();                  
                  }            
              });
              return roles;
          }  
    }
    
}
