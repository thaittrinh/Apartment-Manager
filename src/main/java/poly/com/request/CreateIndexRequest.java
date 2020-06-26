package poly.com.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.com.entity.Apartment;
import poly.com.entity.Employee;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndexRequest {
	
	
	//length 8
	private Apartment apartment;

	private int electricityNumber;
	
	private int warterNumber;
	
	private Date date;
	
	private Employee employee;
 
}
