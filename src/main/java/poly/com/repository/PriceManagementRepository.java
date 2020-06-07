package poly.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.com.entity.PriceManagement;

public interface PriceManagementRepository extends JpaRepository<PriceManagement, Integer> {
	@Query("select  w from PriceManagement w where year(w.date) = ?1 and month(w.date) = ?2")
	List<PriceManagement> findByYearAndMonth(int year, int month);
}