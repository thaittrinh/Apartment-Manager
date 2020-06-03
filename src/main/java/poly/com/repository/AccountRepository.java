package poly.com.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import poly.com.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

  Optional<Account> findByUsername(String username); //
	
  Boolean existsByUsername(String username); 
}
