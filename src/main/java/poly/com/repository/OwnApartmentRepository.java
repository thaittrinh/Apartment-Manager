package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.OwnApartment;

public interface OwnApartmentRepository extends JpaRepository<OwnApartment, Integer> {

	 Boolean existsByIdentitycard(String identitycard); 
}
