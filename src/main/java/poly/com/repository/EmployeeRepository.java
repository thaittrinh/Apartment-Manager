package poly.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import poly.com.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("update Employee c set c.password = :password where  c.id = :id ")
    Employee updatepassword(String password, int id);

    Employee findByPassword(String password);

    Optional<Employee> findByPhone(String phone);

    Optional<Employee> findByIdentitycard(String identitycard);

    Optional<Employee> findByUsername(String username);


}