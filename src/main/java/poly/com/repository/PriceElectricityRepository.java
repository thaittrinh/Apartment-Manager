package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import poly.com.entity.PriceElectricity;
import poly.com.entity.PriceGarbage;

import java.util.List;

public interface PriceElectricityRepository extends JpaRepository<PriceElectricity, Integer> {

    @Query("select w from PriceElectricity  w where w.limits = :limit")
    List<PriceElectricity> findByLimit( int limit);

}
