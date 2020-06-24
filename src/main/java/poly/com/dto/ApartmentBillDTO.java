package poly.com.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentBillDTO {

	private int id;
	
	private String idApartment;
	
	private Date date;
	
	private double totalPrice;
	
	private String nameEmployee;
	
	private boolean paid;
}
