package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
