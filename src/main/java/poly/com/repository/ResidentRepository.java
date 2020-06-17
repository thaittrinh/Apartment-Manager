package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poly.com.entity.Resident;

import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository <Resident,Integer> {

    Optional<Resident> findByIdentitycard(String identitycard);
}
