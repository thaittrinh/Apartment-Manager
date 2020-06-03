package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
