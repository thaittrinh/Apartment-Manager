package poly.com.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndexRequest {

	private int electricityNumber;
	
	private int warterNumber;
	
	private Date date;
 
}
