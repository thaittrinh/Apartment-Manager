package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.com.repository.ApartmentRepository;

@Service
public class ApartmentService {

	@Autowired
	ApartmentRepository apartmentRepository;
	
	
	
}
