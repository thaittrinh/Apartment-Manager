package poly.com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.com.entity.Contact;


public interface ContactRepository  extends JpaRepository<Contact,Integer>{

	Optional<Contact> findTopByOrderByIdDesc();
	
}
