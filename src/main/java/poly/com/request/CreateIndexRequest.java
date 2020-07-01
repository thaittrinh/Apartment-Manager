package poly.com.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.com.entity.Apartment;
import poly.com.entity.Employee;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndexRequest {
	
	@NotBlank(message = "Apartment is not null!")
	private Apartment apartment;

	@NotBlank(message = "Electricity number is not null!")
	private int electricityNumber;
	
	@NotBlank(message = "Water number is not null!")
	private int warterNumber;
	
	private Date date;
	
	@NotBlank(message = "Employee is not null!")
	private Employee employee;
 
}
