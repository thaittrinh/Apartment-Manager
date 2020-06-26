package poly.com.request;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import poly.com.entity.Apartment;
import poly.com.entity.Employee;


@Getter
@Setter
public class UpdateIndexRequest extends CreateIndexRequest {
	
	private int bicycleNumber;
	
	private int motocycleNumber;
	
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
