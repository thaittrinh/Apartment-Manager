package poly.com.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.Apartment;
import poly.com.entity.ApartmentIndex;

public interface ApartmentIndexRepository extends JpaRepository<ApartmentIndex, Integer> {

	Optional<ApartmentIndex> findFirstByApartmentAndDateLessThanOrderByDateDesc(Apartment apartment,Date date);	
}
