package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import poly.com.entity.PriceElectricity;
import poly.com.entity.PriceGarbage;

import java.util.List;

public interface PriceElectricityRepository extends JpaRepository<PriceElectricity, Integer> {

    @Query("select p from PriceElectricity p where year(p.date) = ?1 and month(p.date) = ?2 and p.limits = ?3")
    PriceElectricity findByLimit(int year, int month, int limit);

}
