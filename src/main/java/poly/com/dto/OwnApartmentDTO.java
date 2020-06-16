package poly.com.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnApartmentDTO {

	private Integer id;
	
	private String fullname;
	
	private Boolean gender;
	
	private String homeTown;
	
	private String phone;
	
    private String email;
    
    private Date birthday;
    
    private String job;
    
    private String image;
    
    private String identitycard;
    
    private List<String> apartments;
   
	
}
