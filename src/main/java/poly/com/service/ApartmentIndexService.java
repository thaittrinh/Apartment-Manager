package poly.com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import poly.com.constant.MessageError;
import poly.com.dto.ApartmentBillDTO;
import poly.com.dto.ResponseDTO;
import poly.com.entity.Bill;
import poly.com.repository.ApartmentIndexRepository;
import poly.com.repository.BillRepository;
import poly.com.repository.PriceElectricityRepository;
import poly.com.repository.PriceGarbageRepository;
import poly.com.repository.PriceManagementRepository;
import poly.com.repository.PriceParkingRepository;
import poly.com.repository.PriceWaterRepository;
import poly.com.repository.VehicleRespository;
import poly.com.request.CreateIndexRequest;

@Service
public class ApartmentIndexService {

	@Autowired
	ApartmentIndexRepository apartmentIndexRepository;
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	PriceWaterRepository priceWaterRepository;
	
	@Autowired
	PriceManagementRepository priceManagementRepository;
	
	@Autowired
	PriceGarbageRepository priceGarbageRepository;
	
	@Autowired
	PriceElectricityRepository priceElectricityRepository;
	
	@Autowired
	PriceParkingRepository priceParkingRepository;
	
	@Autowired
	VehicleRespository vehicleRespository;
	
	
	public ResponseEntity<ResponseDTO> findAll(){
		try {
			List<Bill> lists = billRepository.findAll();	
			List<ApartmentBillDTO>  listDTOs = new ArrayList<>();			
			for (Bill bill : lists) {
				ApartmentBillDTO dto = new ApartmentBillDTO(bill.getId(), bill.getApartmentIndex().getApartment().getId(),
															bill.getApartmentIndex().getDate(), bill.getTotalPrice(),
															bill.getApartmentIndex().getEmployee().getFullName(),bill.getPaid()	);
				listDTOs.add(dto);
			}	
			
			return  ResponseEntity.ok(new ResponseDTO(listDTOs, null));
		} catch (Exception e) {
			
			return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity<ResponseDTO> create(CreateIndexRequest request){
					
	//	UpdateIndexRequest updateIndexRequest =new UpdateIndexRequest(electricityNumber, warterNumber, date, bicycleNumber, motocycleNumber, carNumber)
		
	    return new ResponseEntity<>(new ResponseDTO(null, MessageError.ERROR_500), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public double test(Date date){
	
		//PriceWater priceWater =  priceWaterRepository.findFirstByDateLessThanEqualOrderByDateDesc(date).orElse(null);
		//PriceManagement management = priceManagementRepository.findFirstByDateLessThanEqualOrderByDateDesc(date).orElse(null);
		//PriceGarbage garbage = priceGarbageRepository.findFirstByDateLessThanEqualOrderByDateDesc(date).orElse(null);
		//PriceElectricity electricity = priceElectricityRepository.findFirstByDateLessThanEqualAndLimitsOrderByDateDesc(date, 100).orElse(null);
		    return vehicleRespository.findTotalVehicelByType("L.B.201", 3);
		    
		    
		}
	
	
	
}




