package poly.com.request;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateIndexRequest extends CreateIndexRequest {
	
	private int bicycleNumber;
	
	private int motocycleNumber;
	
	private int carNumber;
	
	@Builder
	public UpdateIndexRequest(int electricityNumber, int warterNumber, Date date, int bicycleNumber,
							   int motocycleNumber, int carNumber) {
		super(electricityNumber, warterNumber, date);
		this.bicycleNumber = bicycleNumber;
		this.motocycleNumber = motocycleNumber;
		this.carNumber = carNumber;
	}	
	
}
