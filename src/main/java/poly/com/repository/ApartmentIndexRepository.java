package poly.com.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.com.entity.Apartment;
import poly.com.entity.ApartmentIndex;

public interface ApartmentIndexRepository extends JpaRepository<ApartmentIndex, Integer> {

	Optional<ApartmentIndex> findFirstByApartmentAndDateLessThanOrderByDateDesc(Apartment apartment,Date date);	
	
	@Query("select a from ApartmentIndex a where  a.apartment = ?1 and  year(a.date) = ?2 and month(a.date) = ?3")
	Optional<ApartmentIndex> findByApartmentAndYearAndMonth(Apartment apartment ,int year, int month);
	
	Boolean existsByApartment(Apartment apartment);
	
	@Query(value = "SELECT * " + 
			" FROM  apartment_index  " + 
			" WHERE  id_apartment = ?1 and  year(date) = ?2 and month(date)= ?3 ", nativeQuery = true)
	Optional<ApartmentIndex> findByMonthInYear(String id_apartment, int year, int month);
}
