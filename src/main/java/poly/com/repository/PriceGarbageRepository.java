package poly.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.com.entity.PriceGarbage;

public interface PriceGarbageRepository extends JpaRepository<PriceGarbage, Integer> {
	
  @Query("select  w from PriceGarbage w where year(w.date) = ?1 and month(w.date) = ?2")
  List<PriceGarbage> findByYearAndMonth(int year , int month);
	
}
