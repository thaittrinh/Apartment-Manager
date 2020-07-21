package poly.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{
	

}
