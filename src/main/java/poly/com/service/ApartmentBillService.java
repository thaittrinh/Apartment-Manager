package poly.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.com.repository.ApartmentIndexRepository;
import poly.com.repository.BillRepository;

@Service
public class ApartmentBillService {

	@Autowired
	ApartmentIndexRepository apartmentIndexRepository;
	
	@Autowired
	BillRepository billRepository;
	
	
	
	
	
	
}
