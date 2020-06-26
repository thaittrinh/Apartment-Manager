package poly.com.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.com.entity.Apartment;
import poly.com.entity.ApartmentIndex;

public interface ApartmentIndexRepository extends JpaRepository<ApartmentIndex, Integer> {

	Optional<ApartmentIndex> findFirstByApartmentAndDateLessThanOrderByDateDesc(Apartment apartment,Date date);	
	
	@Query("select a from ApartmentIndex a where year(a.date) = ?1 and month(a.date) = ?2")
	Optional<ApartmentIndex> findByYearAndMonth(int year, int month);
}
