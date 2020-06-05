package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.PriceManagement;

public interface PriceManagementRepository extends JpaRepository<PriceManagement, Integer>{
	
}