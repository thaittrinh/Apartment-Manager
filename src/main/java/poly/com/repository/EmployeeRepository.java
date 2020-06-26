package poly.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import poly.com.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("Update Employee c set c.password = :password where  c.id = :id ")
    Employee updatepassword(String password, int id);

    @Query("Select e.username from Employee e where  e.id = ?1 ")
    String findUserNameById(int id);
    
    Employee findByPassword(String password);

    Optional<Employee> findByPhone(String phone);

    Optional<Employee> findByIdentitycard(String identitycard);

    Optional<Employee> findByUsername(String username);


}