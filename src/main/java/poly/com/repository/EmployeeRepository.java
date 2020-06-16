package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.Employee;
import poly.com.entity.Resident;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  Optional<Employee> findByIndentitycardAndPhone(String identitycard, String phone);

}