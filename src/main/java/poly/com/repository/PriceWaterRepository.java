package poly.com.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.com.entity.PriceWater;

public interface PriceWaterRepository extends JpaRepository<PriceWater,Integer> {
	
	Boolean existsByDate(Date date); 
  
	PriceWater findByDate(Date date); // date is unique
	
	@Query("select w from PriceWater w where year(w.date) = ?1 and month(w.date) = ?2")
	List<PriceWater> findByYearAndMonth(int year, int month);
	
}
