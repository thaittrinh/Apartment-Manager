package poly.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.com.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("Select e.username from Employee e where  e.id = ?1 ")
    String findUserNameById(int id);
	
    Optional<Employee> findByPhone(String phone);

    Optional<Employee> findByIdentitycard(String identitycard);

    Optional<Employee> findByUsername(String username);


}