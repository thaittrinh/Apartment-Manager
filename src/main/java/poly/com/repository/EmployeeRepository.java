package poly.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    Employee findByPassword(String password);
    Optional<Employee> findByPhone(String phone);

    Optional<Employee> findByIdentitycard(String identitycard);

    Optional<Employee> findByUsername(String username);


}