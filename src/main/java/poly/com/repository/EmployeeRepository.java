package poly.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  Optional<Employee> findByPhone(String phone);

  Optional<Employee>  findByIndentitycard(String identitycard);

  Optional<Employee> findByUsername(String username); 
  
  

}