package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poly.com.entity.Resident;

@Repository
public interface ResidentRepository extends JpaRepository <Resident,Integer> {
    Boolean existsByIdentitycard(String identitycard);
}
