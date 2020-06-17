package poly.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  Optional<Employee> findByIndentitycardAndPhone(String identitycard, String phone);
  
  Optional<Employee> findByUsername(String username); 
  
  Boolean existsByUsername(String username); 

}