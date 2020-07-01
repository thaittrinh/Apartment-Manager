package poly.com.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import poly.com.entity.Apartment;
import poly.com.entity.Employee;


@Getter
@Setter
public class UpdateIndexRequest extends CreateIndexRequest {
	
	@NotBlank(message = "Bicycle number is not null!")
	private int bicycleNumber;
	
	@NotBlank(message = "Motocycle number is not null!")
	private int motocycleNumber;
	
	@NotBlank(message = "Car number is not null!")
	private int carNumber;
	
	@Builder
	public UpdateIndexRequest(Apartment apartment, int electricityNumber, int warterNumber, Date date,
			Employee employee, int bicycleNumber, int motocycleNumber, int carNumber) {
		super(apartment, electricityNumber, warterNumber, date, employee);
		this.bicycleNumber = bicycleNumber;
		this.motocycleNumber = motocycleNumber;
		this.carNumber = carNumber;
	}
	

}
